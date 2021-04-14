package com.heiku.spring.springbootlearning.injection.simple;

import org.springframework.stereotype.Component;

/**
 * @Author: Heiku
 * @Date: 2021/4/14
 */
@Component
public class SimpleServiceImpl implements SimpleService {

    @Override
    public void print() {
        System.out.println("print");
    }
}
