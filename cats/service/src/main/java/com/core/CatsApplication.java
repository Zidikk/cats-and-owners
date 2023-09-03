package com.core;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = {"com.service.implementation", "com.controllers", "com.security"})
@EnableJpaRepositories(basePackages = {"com.repositories"})
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@EntityScan(basePackages = {"com.models"})
@Import(RabbitMQConfig.class)
@EnableRabbit
public class CatsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatsApplication.class, args);
    }
}
