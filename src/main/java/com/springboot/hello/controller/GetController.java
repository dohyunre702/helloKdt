package com.springboot.hello.controller;

import domain.Dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
@Slf4j
public class GetController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        log.info("hello로 요청이 들어왔습니다.");
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

        log.info("variable1로 요청이 들어왔습니다.");
        return variable;
    }

    //실습1.
    @GetMapping(value = "/request1") //request1을 받는 엔드포인트 만들기
    public String getVariable1(@RequestParam String name, @RequestParam String email, @RequestParam String organization) {//requestParam으로 값 3개
        log.info("request1로 요청이 들어왔습니다.");
        return String.format("%s %s %s", name, email, organization); //받은 값 그대로 리턴
    }

    //실습2.
    @GetMapping(value = "/request2")
    public String getVariable2(@RequestParam Map<String, String> param){
        log.info("request2로 요청이 들어왔습니다.");
        //람다식으로 Map 전체 출력하기
        param.forEach((key, value) -> {
            System.out.println("[key]:" + key + ", [value]:" + value);
        });
        return "호출이 완료되었습니다.";
    }

    //실습3. Dto객체 활용한 GET요청 생성
    @GetMapping(value = "/request3")
    public String getRequestParam(MemberDto memberDto) { //MemberDto 참조변수 선언
        log.info("request3로 요청이 들어왔습니다.");
        return memberDto.toString(); //memberDto의 toString 메소드
    }
}
