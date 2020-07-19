package com.heiku.spring.springbootlearning.injection;

import com.heiku.spring.springbootlearning.entity.User;
import com.heiku.spring.springbootlearning.entity.UserHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * annotation setter
 *
 * @author Heiku
 * @date 2020/7/19
 **/
public class AnnotationDependencySetterInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        // 加载 user bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions("classpath:\\META-INF\\dependency-setter-injection.xml");

        applicationContext.refresh();

        // BeanDefinition
        BeanDefinition beanDefinition = createUserHolderBeanDefinition();
        applicationContext.registerBeanDefinition("userHolder", beanDefinition);

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }

/*    @Bean
    public UserHolder userHolder(User user) {

*//*        // setter 模式
        UserHolder holder = new UserHolder();
        holder.setUser(user);
        return holder;*//*

        // 构造器模式
        return new UserHolder(user);
    }*/


    /**
     * 自动引入 beanDefinition 的属性
     */
    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        builder.addPropertyReference("user","user");
        return builder.getBeanDefinition();
    }
}
