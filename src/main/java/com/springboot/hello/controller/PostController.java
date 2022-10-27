package com.springboot.hello.controller;

import domain.Dto.MemberDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @RequestMapping(value = "/domain", method = RequestMethod.POST)
        public String postEx() {
            return "Hello, this is Post API example";
        }


    @RequestMapping(value = "/domainwparam", method = RequestMethod.POST)
    public String postRequestParam(MemberDto memberDto) {
        return memberDto.toString();
    }
}