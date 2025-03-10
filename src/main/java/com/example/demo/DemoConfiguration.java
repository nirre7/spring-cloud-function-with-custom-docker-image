package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class DemoConfiguration {

    @Bean
    public Supplier<String> ping() {
        return () -> "pong";
    }
}
