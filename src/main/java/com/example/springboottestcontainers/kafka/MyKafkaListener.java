package com.example.springboottestcontainers.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Slf4j
@Profile("kafka")
public class MyKafkaListener {

    @KafkaListener(topics = "mike", groupId = "Mike")
    public void listenGroupFoo(String message) {
        log.info("Yes we received message in group Mike with topic mike: " + message);
    }
}
