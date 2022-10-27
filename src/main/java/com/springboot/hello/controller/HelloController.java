package com.springboot.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello") //controller에 어떤 메소드를 할당할지 정해줌
    public String hello() {
        return "Hello world";
    }

}
