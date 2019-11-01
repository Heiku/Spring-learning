package com.heiku.spring.springbootlearning.post;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: Heiku
 * @Date: 2019/10/25
 */

@Component
public class SimpleInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass != Hello.class){
            return null;
        }
        System.out.println("InstantiationAwareBeanPostProcessor before");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean instanceof Hello) {
            System.out.println("InstantiationAwareBeanPostProcessor after");
            return true;
        }
        return false;
    }

}
