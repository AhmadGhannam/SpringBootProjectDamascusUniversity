package com.demo.microservicetwo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.microservicetwo.controllers.ServiceTwoListener;

import java.util.LinkedList;
import java.util.Queue;

@RestController
public class AppController {


	@Autowired
	private ServiceTwoListener serviceTwoListener;



	@GetMapping("/api/queue")
	public ResponseEntity<Queue<String>> getQueue() {
		Queue<String> queue = serviceTwoListener.getMyQueue();
		serviceTwoListener.setMyQueue(new LinkedList<>());
		return ResponseEntity.ok(queue);
	}
}
