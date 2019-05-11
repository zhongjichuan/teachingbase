package com.teachingbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class TeachingbaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeachingbaseApplication.class, args);
    }

}
