package com.example.sparkdemo.controller;

import com.example.sparkdemo.model.Number;
import com.example.sparkdemo.model.Sum;
import com.example.sparkdemo.service.SumOfNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumOfNumbersController {

    @Autowired
    private SumOfNumbersService sumOfNumbersService;

    @GetMapping("/sumofnumbers")
    public Sum getSumOfNumbers(@Validated @RequestBody String numbers) {
        return this.sumOfNumbersService.getSumOfNumbers(numbers);
    }
}
