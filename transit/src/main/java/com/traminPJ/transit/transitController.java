package com.traminPJ.transit;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/transit")
public class transitController {

    public Storage storage;
    @Autowired
    RestTemplate rest;
    @Autowired
    transitService Service;


    @Autowired
    public transitController(Storage storage) {
        this.storage = storage;
    }






    @PostMapping("/addtransit")
    public String addTransit(@RequestBody Transit transit) {

        return Service.addTransit(transit);
    }

    @GetMapping("/gettransit")
    public List<Transit> getTransits() {

        return Service.getTransits();
    }

    @GetMapping("/addrandomtransit")
    public String  addRandomTransit() {

        return Service.addRandomTransit();
    }

    @GetMapping("/checktransit/{id}")
    public String checkTransit(@PathVariable String id) {
       return Service.checkTransit(id);
    }

    @PutMapping("/edittransit/{id}")
    public String editTransit(@PathVariable String id, @RequestBody Transit updatedTransit) {
        return Service.editTransit(id,updatedTransit);
    }

    @GetMapping("/fallback")
    public String fallbacktest() {

        return Service.FallbackTest();
    }
}
