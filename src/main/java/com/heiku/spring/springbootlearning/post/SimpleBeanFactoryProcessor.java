package com.heiku.spring.springbootlearning.post;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Heiku
 * @Date: 2019/10/24
 */

@Component
public class SimpleBeanFactoryProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("beanFactoryPostProcessor start");

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("hello");
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        if (mpv.contains("name")){
            mpv.add("name", "Heiku");
        }
    }
}
