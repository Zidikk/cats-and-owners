package com.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


@Configuration
public class RabbitMQConfig {

    @Bean
    Queue queueAdd() {
        return new Queue("friend.queue.add", true);
    }

    @Bean
    Queue queueDelete() {
        return new Queue("friend.queue.delete", true);
    }

    @Bean
    Queue queueDeleteById() {
        return new Queue("friend.queue.deleteById", true);
    }

    @Bean
    Queue queueDeleteAll() {
        return new Queue("friend.queue.deleteAll", true);
    }

    @Bean
    Queue queueGetById() {
        return new Queue("friend.queue.getById", true);
    }

    @Bean
    Queue queueGetAll() {
        return new Queue("friend.queue.getAll", true);
    }

    @Bean
    Exchange myExchange() {
        return ExchangeBuilder.directExchange("friend.exchange").durable(true).build();
    }

    @Bean
    Binding bindingAdd() {
        return BindingBuilder
                .bind(queueAdd())
                .to(myExchange())
                .with("friend.add")
                .noargs();
    }

    @Bean
    Binding bindingDelete() {
        return BindingBuilder
                .bind(queueDelete())
                .to(myExchange())
                .with("friend.delete")
                .noargs();
    }

    @Bean
    Binding bindingDeleteById() {
        return BindingBuilder
                .bind(queueDeleteById())
                .to(myExchange())
                .with("friend.deleteById")
                .noargs();
    }

    @Bean
    Binding bindingDeleteAll() {
        return BindingBuilder
                .bind(queueDeleteAll())
                .to(myExchange())
                .with("friend.deleteAll")
                .noargs();
    }

    @Bean
    Binding bindingGetById() {
        return BindingBuilder
                .bind(queueGetById())
                .to(myExchange())
                .with("friend.getById")
                .noargs();
    }

    @Bean
    Binding bindingGetAll() {
        return BindingBuilder
                .bind(queueGetAll())
                .to(myExchange())
                .with("friend.getAll")
                .noargs();
    }

    @Bean
    public org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .modules(new JavaTimeModule())
                .dateFormat(new StdDateFormat())
                .build().registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}