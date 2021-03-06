# pub-sub

* Spring Boot, Spring Web
* Postgres
* Lombok
* Gradle

## Make

Для упрощения управления вместо `gradle` можно использовать `make`:

```bash
$ make build  # gradle bootJar
$ make run-subscriber  # java -jar subscriber/build/libs/*.jar 
$ make run-publisher  # java -jar publisher/build/libs/*.jar  
$ make tests  # gradle test
$ make clean  # gradle clean
```

## Запуск 

Для запуска потребуется Postgres. 
```shell
$ docker run --name subscriber-postgres -p 5432:5432 -e POSTGRES_USER=subscriber -e POSTGRES_PASSWORD=subscriber -d postgres
```

```shell
$ make build
$ make run-subscriber
```

После того как запустится subscriber, можно запускать publisher:

```shell
$ make run-publisher
```

## Задача

Необходимо реализовать сервис, состоящий из 2 приложений. Первое
приложение — publisher — отвечает за генерацию и отправку сообщений подписчику
(subscriber). Subscriber разбирает сообщение и по определенной логике
записывает его в базу данных.

### Протокол

Publisher и subscriber общаются между собой методом отправки JSON сообщений по
следующему протоколу:

* **ID**. Идентификатор отправленного сообщения. Автоматический инкремент в 
  каждом сообщении.
* **MSISDN**. Уникальный номер абонента. В рамках этого задания рандомно
сгенерированное цифровое значение.
* **Action**. Тип сообщения. Сообщения могут быть 2 типов – PURCHASE или
SUBSCRIPTION. Значение выбирается рандомно при генерации сообщения.
* **Timestamp**. UNIX timestamp.

Пример сообщения:
```json
{
  "id": 1,
  "msisdn": 123456789,
  "action": "PURCHASE",
  "timestamp": 1589464122
}
```

### Publisher

* Имеет модуль генерации описанных выше сообщений.
* Имеет модуль отправки сгенерированных сообщений. Отправка сообщений
осуществляется по протоколу HTTP по средствам POST запросов на Subscriber.

Важно что бы Publisher осуществлял многопоточную отправку сообщений из 5
потоков. Пауза между отправками сообщений в каждом потоке должна быть 15
секунд

### Subscriber
* Принимает отправленные запросы по HTTP протоколу.
* Осуществляет парсинг сообщений.
* Сохраняет полученные данные в БД
* `Action.PURCHASE` должен сохраняться в таблицу `PURCHASE`, а 
  `Action.SUBSCRIPTION` в таблицу `SUBSCRIPTION`
  

### Общие требования:

* Использование JAVA, Spring (модули на усмотрение)
* Все данные хранятся в БД (предпочтительно Postgres)
* Наличие текстового логирования

## Реализация

За основу был взять Spring Boot с Spring Web, в качестве базы данных
используется Postgres.

Для возможности повторного использования общие объекты 
`MessageDto` и `Action` были вынесены в отдельную зависимость.

### Publisher

Настройки:

* `client.subscriber.host` - путь до корневого каталога сервиса подписчика, 
  по умолчанию `http://localhost:8080`.
* `worker.pool-size` - количество потоков, одновременно выполняющих работу,
  по умолчанию 5.
* `worker.tasks-count` - количество задач, которое будет запланировано на 
  старте сервиса, по умолчанию 5.
* `worker.rate-sec` - период запуска созданных задач в секундах, 
  по умолчанию 15.
  
На старте сервис создаёт `worker.tasks-count` задач с периодическим запуском
в `worker.rate-sec`. Задачи запускает `ScheduledThreadPoolExecutor` с пулом
в `worker.pool-size` потоков.

В ходе каждого нового выполнения задачи сервис создаёт новый `MessageDto` со
случайными `Action` и `MSISDN` и `ID` в виде уникальной последовательности.
После создания объект отправляется по HTTP сервису подписчика.

Запуск сервиса, создание задачи и их выполнение логируется.

### Subscriber

Настройки:
* `spring.datasource.url` - адрес подключения к БД, 
  по умолчанию `jdbc:postgresql://localhost:5432/postgres`
* `spring.datasource.username` - логин от БД, по умолчанию `subscriber`
* `spring.datasource.password` - пароль от БД, по умолчанию `subscriber`

Сервис принимает сообщение по HTTP, по `Action` определяет обработчика и
передаёт ему сообщение. Каждый обработчик конвертирует полученный 
`MessageDto` в свою сущность и сохраняет с использованием соответствующего
репозитория.