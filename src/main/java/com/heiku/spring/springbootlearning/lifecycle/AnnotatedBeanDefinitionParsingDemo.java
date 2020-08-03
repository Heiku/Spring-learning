package com.heiku.spring.springbootlearning.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解 BeanDefinition 解析
 *
 * @author Heiku
 * @date 2020/8/2
 **/
public class AnnotatedBeanDefinitionParsingDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();

        // 注册当前类 (非 @Component 类)
        reader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        System.out.printf("AnnotatedBeanDefinitionReader register bean, before = %d, after = %d\n", beanDefinitionCountBefore, beanDefinitionCountAfter);

        AnnotatedBeanDefinitionParsingDemo demo = beanFactory.getBean(AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(demo);
    }
}
