package com.heiku.spring.springbootlearning.beanDefinition;

import com.heiku.spring.springbootlearning.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Heiku
 * @Date: 2020/9/23
 */
public class SimpleBeanDefinitionRegisterDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.heiku.*");
        //applicationContext.register(SimpleBeanDefinitionRegisterDemo.class);
        //applicationContext.refresh();

        BeanFactory beanFactory = applicationContext.getBeanFactory();
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }

    @Bean
    public User user() {
        return new User("Heiku", 12);
    }
}
