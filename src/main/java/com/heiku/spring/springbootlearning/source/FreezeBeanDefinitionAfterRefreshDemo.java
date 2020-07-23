package com.heiku.spring.springbootlearning.source;

import com.heiku.spring.springbootlearning.entity.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author Heiku
 * @date 2020/7/22
 **/
public class FreezeBeanDefinitionAfterRefreshDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(FreezeBeanDefinitionAfterRefreshDemo.class);
        applicationContext.refresh();

        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("name", "Heiku");
        builder.addPropertyValue("age", 12);
        applicationContext.registerBeanDefinition("user", builder.getBeanDefinition());

        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));

        applicationContext.close();
    }
}
