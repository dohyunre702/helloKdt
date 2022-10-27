package com.springboot.hello.controller;

import domain.Dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    //실습 5. String 방식 put api 생성
    @RequestMapping(value = "/domain", method = RequestMethod.PUT)
        public String putEx() {
            return "Hello, this is Post API example";
        }

    //실습 5-1. Map 활용
    @RequestMapping(value = "/domain1", method = RequestMethod.PUT)
    public String putRequestParam1(@RequestBody Map<String, Object> putData) {
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach((map) -> {
            sb.append(map.getKey() + ":" + map.getValue() + "\n");
        });
        return sb.toString();
    }

    //실습 5-2. memberDto 객체 가져오기
    @RequestMapping(value = "/domain2", method = RequestMethod.PUT)
    public String putRequestParam2(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }
    //RequestBody : Body 내용을 지정된 객체에 매핑


    //실습 5-3. HttpEntity
    @PutMapping(value = "/domain3")
    public ResponseEntity<MemberDto> postMemberDao3(@RequestBody MemberDto memberDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}