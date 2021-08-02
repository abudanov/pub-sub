package io.github.abudanov.pubsub.publisher.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SendMessageTaskExecutorWorker {

    private final SendMessageTask task;
    private final ScheduledExecutorService executorService;
    private final int corePoolSize;
    private final int tasksCount;
    private final long rate;


    public SendMessageTaskExecutorWorker(
            @Autowired SendMessageTask task,
            @Value("${worker.pool-size:1}") int corePoolSize,
            @Value("${worker.tasks-count:5}") int tasksCount,
            @Value("${worker.rate-sec:15}") long rate
    ) {
        this.executorService = Executors.newScheduledThreadPool(corePoolSize);
        this.task = task;
        this.corePoolSize = corePoolSize;
        this.tasksCount = tasksCount;
        this.rate = rate;
    }

    @PostConstruct
    void doWork() {
        log.info(String.format("Starting %d tasks with pool size %d", tasksCount, corePoolSize));
        for (int i = 0; i < tasksCount; i++) {
            log.info(String.format("Creating task #%d with rate %d sec", i, rate));
            executorService.scheduleAtFixedRate(task, 0, rate, TimeUnit.SECONDS);
        }
    }

}
