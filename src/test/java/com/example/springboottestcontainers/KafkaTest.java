package com.example.springboottestcontainers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ActiveProfiles("kafka")
public class KafkaTest {

    static KafkaContainer kafka;

    @Autowired
    KafkaTemplate kafkaTemplate;


    @BeforeAll
    public static void beforeAll() {
        kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));
        kafka.start();
        String bootstrapServers = kafka.getBootstrapServers();
        System.out.println("Bootstrap servers " + bootstrapServers);
        System.setProperty("kafka.bootstrapAddress", bootstrapServers);
    }

    @Test
    void testSpaceShipControllerMocked() throws InterruptedException {
        kafkaTemplate.send("mike", "First Message");
        kafkaTemplate.send("mike", "Second Message");
        kafkaTemplate.send("susan", "Third Message");
        for (int i = 0; i < 400; i++) {
            if (i % 25 == 0) {
                kafkaTemplate.send("mike", "Send another message i is " + i);
            }
            Thread.sleep(10);
        }
    }


}
