package com.heiku.spring.springbootlearning.lifecycle;

import com.heiku.spring.springbootlearning.entity.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean 元信息配置
 *
 * @author Heiku
 * @date 2020/8/2
 **/
public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 Properties 加载 beanDefinition
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "/META-INF/user.properties";

        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        int beanNums = reader.loadBeanDefinitions(encodedResource);
        System.out.printf("current beanNum = %d\n", beanNums);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
