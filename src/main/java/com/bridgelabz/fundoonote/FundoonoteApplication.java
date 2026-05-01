package com.bridgelabz.fundoonote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FundoonoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundoonoteApplication.class, args);
    }
}