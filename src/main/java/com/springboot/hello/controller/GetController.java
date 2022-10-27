package com.springboot.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        return "Hello World";
    }

    //매개변수 없는 GET 메서드 구현
    @GetMapping(value = "/name")
    public String getName() {
        return "Flature";
    }

    //(@PathVariable)매개변수 활용한 구현
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable(@PathVariable String variable) {
        return variable;
    }

    //실습1.
    @GetMapping(value = "/request1") //request1을 받는 엔드포인트 만들기
    public String getVariable2(@RequestParam String name, @RequestParam String email, @RequestParam String organization) {//requestParam으로 값 3개
        return String.format("%s %s %s", name, email, organization); //받은 값 그대로 리턴
    }


}
