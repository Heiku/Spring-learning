package com.heiku.spring.springbootlearning.injection;

import com.heiku.spring.springbootlearning.entity.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * xml setter 依赖注入
 *
 * @author Heiku
 * @date 2020/7/19
 **/
public class XmlDependencySetterInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String path = "classpath:\\META-INF\\dependency-setter-injection.xml";
        reader.loadBeanDefinitions(path);

        UserHolder userHolder = (UserHolder) beanFactory.getBean("userHolder");
        System.out.println(userHolder);
    }
}
