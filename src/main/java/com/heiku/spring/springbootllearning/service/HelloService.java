package com.heiku.spring.springbootllearning.service;

import org.springframework.stereotype.Component;

/**
 * @Author: Heiku
 * @Date: 2019/10/10
 */

@Component
public class HelloService {

    void sayHello(){
        System.out.println("hello");
    }
}
