package com.springboot.hello.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/get-api")
@Slf4j //로그 남기기
public class HelloController {
    @RequestMapping("/hello", method = RequestMethod.GET) //controller에 어떤 메소드를 할당할지 정해줌
    public String hello() {
        log.info("hello로 요청이 들어왔습니다.");
        return "Hello world";
    }

    @GetMapping(value = "/name")
    public String getName() {
        log.info("getName으로 요청이 들어왔습니다.");
        return "DH";
    }

    @GetMapping(value="variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        log.info("getVariable1으로 요청이 들어왔습니다. variable:{}", variable);
        return variable;
    }
}
