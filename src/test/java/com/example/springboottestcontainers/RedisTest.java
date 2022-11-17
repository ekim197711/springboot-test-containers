package com.example.springboottestcontainers;

import com.example.springboottestcontainers.space.SpaceShip;
import com.example.springboottestcontainers.space.SpaceShipRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@SpringBootTest
@Testcontainers
@ActiveProfiles("redis")
class RedisTest {
    static GenericContainer redis;
    @Autowired
    SpaceShipRestController spaceShipRestController;

    @BeforeAll
    static void beforeAll() {
        redis = new GenericContainer(DockerImageName.parse("redis"))
                .withExposedPorts(6379);
        redis.start();
        System.out.println("Fetch settings");
        System.setProperty("spring.redis.host", redis.getHost());
        System.setProperty("spring.redis.port", redis.getMappedPort(6379).toString());
        System.out.println("The local port mapped to 6379 is " + redis.getMappedPort(6379).toString());
    }

    @Test
    void contextLoads() {
        List<SpaceShip> spaceShips = spaceShipRestController.allShips();
        int firstCount = 0;
        if (spaceShipRestController.allShips() != null)
            firstCount = spaceShips.size();
        spaceShipRestController.newShip("Mike");
        spaceShipRestController.newShip("Susan");
        spaceShips = spaceShipRestController.allShips();
        int secondCount = spaceShips.size();
        Assertions.assertEquals(firstCount + 2, secondCount);
        spaceShips.forEach(System.out::println);
    }
}
