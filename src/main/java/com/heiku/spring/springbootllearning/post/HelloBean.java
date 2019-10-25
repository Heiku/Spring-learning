package com.heiku.spring.springbootllearning.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: Heiku
 * @Date: 2019/10/24
 */

@Component
public class HelloBean {

    @Bean(name = "hello", initMethod = "myInitMethod")
    public Hello hello(){
        return new Hello("mike", "hello");
    }
}
