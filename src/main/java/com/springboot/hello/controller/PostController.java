package com.springboot.hello.controller;

import domain.Dto.MemberDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    //실습 4. post api 생성
    @RequestMapping(value = "/domain", method = RequestMethod.POST)
        public String postEx() {
            return "Hello, this is Post API example";
        }

    //실습 4-2. Map 활용
    @RequestMapping(value = "/domain1", method = RequestMethod.POST)
    public String postRequestParam1(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach((map) -> {
            sb.append(map.getKey() + ":" + map.getValue() + "\n");
        });
        return sb.toString();
    }

    //실습 4-1. post api_memberDto 객체 가져오기
    @RequestMapping(value = "/domain2", method = RequestMethod.POST)
    public String postRequestParam2(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }
    //RequestBody : Body 내용을 지정된 객체에 매핑

}