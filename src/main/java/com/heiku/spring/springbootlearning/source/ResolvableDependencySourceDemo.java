package com.heiku.spring.springbootlearning.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 非 Spring 管理的对象 ResolvableDependency 依赖来源
 *
 * @author Heiku
 * @date 2020/7/22
 **/
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println("resolvableDependency =  " + value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencySourceDemo.class);

        // 在刷新前，构建 beanPostProcessor， 将 string 加入到 resolvableDependency
/*
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "Hello, World");
        });
*/
        applicationContext.getBeanFactory().registerResolvableDependency(String.class, "Hello, World");
        applicationContext.refresh();
        applicationContext.close();
    }
}
