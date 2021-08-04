package io.github.abudanov.pubsub.publisher.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendMessageTaskExecutorWorker {

    private final SendMessageTask task;
    private final ScheduledExecutorService executorService;

    @Value("${worker.tasks-count}")
    private int tasksCount;

    @Value("${worker.rate-sec}")
    private long rate;

    @PostConstruct
    void doWork() {
        log.info("Starting {} tasks...", tasksCount);
        for (int i = 0; i < tasksCount; i++) {
            log.info("Creating task #{} with rate {} sec", i, rate);
            executorService.scheduleAtFixedRate(task, 0, rate, TimeUnit.SECONDS);
        }
    }
}
