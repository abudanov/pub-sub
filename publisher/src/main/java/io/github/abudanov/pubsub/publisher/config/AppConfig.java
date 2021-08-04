package io.github.abudanov.pubsub.publisher.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class AppConfig {

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    ScheduledExecutorService getScheduledExecutorService(
            @Value("${worker.pool-size}") int corePoolSize
    ) {
        return Executors.newScheduledThreadPool(corePoolSize);
    }
}
