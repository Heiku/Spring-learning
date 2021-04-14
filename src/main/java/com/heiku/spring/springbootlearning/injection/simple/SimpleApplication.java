package com.heiku.spring.springbootlearning.injection.simple;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: Heiku
 * @Date: 2021/4/14
 */
public class SimpleApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SimpleConfig.class);
        SimpleService simpleService = applicationContext.getBean(SimpleService.class);
        simpleService.print();
    }
}
