package com.careerlink.job.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/job-test")
    public String test() {
        return "Job Service JWT authentication is working";
    }
}