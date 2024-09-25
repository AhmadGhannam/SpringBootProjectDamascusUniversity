package com.demo.microservicethree.controllers;

import com.demo.microservicethree.MyService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AppController {

	private final MyService myService;

	public AppController(MyService myService) {

		this.myService = myService;
	}



	@RequestMapping("/")
	public ModelAndView homee()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sendMessage");
		return modelAndView;
	}

	// Handle form submission
	@PostMapping("/send")
	@ResponseBody
	public String sendMessage(@RequestBody MessageDTO messageDTO, Model model) {
		myService.sendMessageToServiceTwo(messageDTO.getMessage());
		System.out.println("Message sent: " + messageDTO.getMessage());
		model.addAttribute("confirmation", "Message sent to Service Two");
		return "Message sent";
	}



}










