package com.demo.microservicetwo.controllers;

import com.demo.microservicetwo.controllers.ServiceTwoListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    static final String QUEUE_NAME = "trainQueue";


//    the second parameter is false), meaning it won't survive a broker restart.
    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

//    which is used to send and receive messages.
//    It requires a ConnectionFactory to connect to the RabbitMQ broker.
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

//     Defines a bean for MessageListenerAdapter, which adapts the ServiceTwoListener to a message listener.
//     receiveMessage: The method in ServiceTwoListener that will handle the received messages.

    @Bean
    MessageListenerAdapter listenerAdapter(ServiceTwoListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
