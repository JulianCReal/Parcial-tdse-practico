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
    public Mathsource catalan(@RequestParam(value = "number") int number) {
        return new Mathsource(counter.incrementAndGet(), calcular(number));
    }

    private ArrayList calcular(int number){
        ArrayList secuence = new ArrayList<>();
        int newnumber = 1;
        while(newnumber < number){
            int rpta = 0;
            int factor = fact(newnumber);
            int faactor1 = fact(newnumber + 1);
            int numerad = fact(newnumber * 2);
            int denom = factor * faactor1;
            rpta = numerad / denom;
            secuence.add(rpta);
        }
        return secuence;
    }

    private int fact(int num){
        int newnum = 2;
        int rpta = 1;
        while (newnum < num){
            rpta *= newnum;
            newnum ++;
        }
        return rpta;
    }
}
