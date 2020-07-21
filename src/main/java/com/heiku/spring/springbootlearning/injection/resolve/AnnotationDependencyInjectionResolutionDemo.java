package com.heiku.spring.springbootlearning.injection.resolve;

import com.heiku.spring.springbootlearning.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 注解驱动的依赖注入过程
 *
 * @author Heiku
 * @date 2020/7/21
 **/
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    private User user;  // DependencyDescriptor ->
    // (required = true) 实时注入 + 类型依赖查找（User.class） + 字段名称 ("user")

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);
        applicationContext.refresh();

        applicationContext.close();
    }

    @Bean
    public User user() {
        return new User("Heiku", 12);
    }
}
