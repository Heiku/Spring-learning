package com.heiku.spring.springbootlearning.lifecycle;

import com.heiku.spring.springbootlearning.entity.ScopeEntity;
import com.heiku.spring.springbootlearning.entity.User;
import com.heiku.spring.springbootlearning.entity.UserHolder;
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
        System.out.println(user);

        applicationContext.close();
    }

    static class MyInitializationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "user") && User.class.equals(bean.getClass())) {
                User user = (User) bean;
                user.setName("Heiku before");
                System.out.println(user);
                return user;
            }
            return null;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "user") && User.class.equals(bean.getClass())) {
                User user = (User) bean;
                user.setName("Heiku after");
                System.out.println(user);
                return user;
            }
            return null;
        }
    }


    @Bean
    public User user() {
        return new User("Heiku", 12);
    }

    @Bean
    public ScopeEntity entity() {
        //return new ScopeEntity(6790L, "Heiku");
        ScopeEntity entity = new ScopeEntity();
        entity.setId(6790L);
        entity.setName("Heiku");
        return entity;
    }

    @Bean
    public UserHolder userHolder() {
        User user = new User("heiku", 12);
        return new UserHolder(user);
    }
}
