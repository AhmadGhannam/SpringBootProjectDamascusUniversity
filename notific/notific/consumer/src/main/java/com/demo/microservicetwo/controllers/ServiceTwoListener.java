package com.demo.microservicetwo.controllers;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class ServiceTwoListener {

    Queue<String> myQueue=new LinkedList<>();

    public Queue<String> getMyQueue() {
        return myQueue;
    }

    public void setMyQueue(Queue<String> myQueue) {
        this.myQueue = myQueue;
    }

    public void receiveMessage(String message) {
        myQueue.add(message);
        System.out.println("Received message: " + message);
        // Process the message
    }
}
