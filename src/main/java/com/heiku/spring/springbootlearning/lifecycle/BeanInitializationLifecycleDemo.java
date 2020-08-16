package com.heiku.spring.springbootlearning.lifecycle;

import com.heiku.spring.springbootlearning.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ObjectUtils;

/**
 * Bean 初始化生命周期
 *
 * @author Heiku
 * @date 2020/8/9
 **/
public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationLifecycleDemo.class);
        DefaultListableBeanFactory beanFactory = applicationContext.getDefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInitializationAwareBeanPostProcessor());
        applicationContext.refresh();

        User user = beanFactory.getBean(User.class);
        System.out.printf("beanFactory get bean: user= %s\n", user.toString());

        applicationContext.close();
    }

    static class MyInitializationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "user") && User.class.equals(bean.getClass())) {
                User user = (User) bean;
                user.setName("Heiku before");
                System.out.printf("beanPostProcessor before Initialization: user= %s\n", user.toString());
                return user;
            }
            return null;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "user") && User.class.equals(bean.getClass())) {
                User user = (User) bean;
                user.setName("Heiku after");
                System.out.printf("beanPostProcessor after Initialization: user= %s\n", user.toString());
                return user;
            }
            return null;
        }
    }

    @Bean(initMethod = "customInitMethod")
    public User user() {
        return new User("Heiku", 12);
    }
}
