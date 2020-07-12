package com.heiku.spring.springbootlearning.lookup;

import com.heiku.spring.springbootlearning.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全查找
 *
 * @author Heiku
 * @date 2020/7/12
 **/
public class TypeSafetyDependencyLookUpDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalBeanFactoryDemo.class);
        applicationContext.refresh();

        // beanFactory 安全性
        // 都不安全，获取不到对象会抛出运行时异常
        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);

        // 使用 objectProvider.getIfAvailable()
        // objectProvider.stream()
        // (ListableBeanFactory) getBeanFactory().getBeansOfType()
        applicationContext.close();
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        try {
            beanFactory.getBean(User.class);
        }catch (BeansException e) {
            e.printStackTrace();
        }
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
        objectFactory.getObject();
    }
}
