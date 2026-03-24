package com.eci.tdse.math_service;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController{
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/catalan")
    public Mathsource catalan(@RequestParam(value = "number") String number) {

        return new Mathsource(counter.incrementAndGet(), catalan(number));
    }

    private String calcular(int number){
        ArrayList sec = new ArrayList(number);


    }
}
