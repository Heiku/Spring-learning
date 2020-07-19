package com.heiku.spring.springbootlearning.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * aware 接口回调
 *
 * @author Heiku
 * @date 2020/7/19
 **/
public class AwareInterfaceDependencyInjectDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AwareInterfaceDependencyInjectDemo.class);
        applicationContext.refresh();

        System.out.println(AwareInterfaceDependencyInjectDemo.beanFactory == applicationContext.getBeanFactory());  // true
        System.out.println(AwareInterfaceDependencyInjectDemo.applicationContext == applicationContext);    // true

        applicationContext.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        AwareInterfaceDependencyInjectDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectDemo.applicationContext = applicationContext;
    }
}
