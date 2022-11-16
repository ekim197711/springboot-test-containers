package com.example.springboottestcontainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

public class SpringbootTestContainersApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTestContainersApplication.class, args);
    }

}
