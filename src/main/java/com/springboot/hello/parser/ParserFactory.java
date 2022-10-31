package com.springboot.hello.parser;

import com.springboot.hello.domain.Hospital;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//ioc, di하는 부분
@Configuration
public class ParserFactory {

    //팩토리에서 조립해주기
    @Bean
    public ReadlLineContext<Hospital> hospitalReadlLineContext() {
        return new ReadlLineContext<Hospital>(new HospitalParser());
    }


}
