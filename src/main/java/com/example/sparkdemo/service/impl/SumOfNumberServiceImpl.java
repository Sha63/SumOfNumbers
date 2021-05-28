package com.example.sparkdemo.service.impl;

import com.example.sparkdemo.model.Sum;
import com.example.sparkdemo.service.SumOfNumbersService;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SumOfNumberServiceImpl implements SumOfNumbersService {

    @Autowired
    private SparkSession sparkSession;

    @Override
    public Sum getSumOfNumbers(String numbers) {

        JavaSparkContext sc = JavaSparkContext.fromSparkContext(sparkSession.sparkContext());

        JavaRDD<String> nums_lines = sc.parallelize(Arrays.asList(numbers.split("\\r?\\n")));
        JavaRDD<String> nums = nums_lines.flatMap(line -> Arrays.asList(line.split("\\s+")).iterator());


        JavaRDD<String> validNumbers = nums.filter(number -> !number.isEmpty());

        JavaRDD<Long> longNumbers = validNumbers.map(number -> Long.valueOf(number));
        Sum sum = new Sum(longNumbers.reduce((x, y) -> x + y));
        return sum;
    }
}
