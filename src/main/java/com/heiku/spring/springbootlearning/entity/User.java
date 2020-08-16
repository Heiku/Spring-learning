package com.heiku.spring.springbootlearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * user entity
 *
 * @author Heiku
 * @date 2020/7/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements InitializingBean, SmartInitializingSingleton, DisposableBean {

    private String name;

    private int age;

    /**
     * 依赖于注解驱动
     * <p>
     * 由 CommonAnnotationBeanPostProcessor
     */
    @PostConstruct
    public void init() {
        System.out.printf("initialization postConstruct: user=%s\n", this.toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.printf("initialization afterPropertiesSet: user=%s\n", this.toString());
    }

    public void customInitMethod() {
        System.out.printf("initialization customInitMethod: user=%s\n", this.toString());
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.printf("afterSingletonsInstantiated: user=%s\n", this.toString());
    }

    @PreDestroy
    public void end() {
        System.out.printf("destruction @PreDestroy: user=%s\n", this.toString());
    }

    @Override
    public void destroy() throws Exception {
        System.out.printf("destruction disposableBean: user=%s\n", this.toString());
    }

    public void customDestroyMethod() {
        System.out.printf("destruction customDestroyMethod: user=%s\n", this.toString());
    }
}
