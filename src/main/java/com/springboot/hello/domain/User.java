package com.springboot.hello.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class User {
    private String id;
    private String name;
    private String password;
}
