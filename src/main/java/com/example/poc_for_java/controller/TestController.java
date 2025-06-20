package com.example.poc_for_java.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
@Tag(name = "Test Controller", description = "Sample endpoints")
public class TestController {

    @Operation(summary = "Get hello message")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Swagger!";
    }
}