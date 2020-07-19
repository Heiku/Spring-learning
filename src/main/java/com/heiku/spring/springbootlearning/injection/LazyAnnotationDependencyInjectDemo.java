package com.heiku.spring.springbootlearning.injection;

import com.heiku.spring.springbootlearning.entity.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 ObjectProvider 进行延迟依赖注入
 *
 * @author Heiku
 * @date 2020/7/19
 **/
public class LazyAnnotationDependencyInjectDemo {

    /**
     * 实时注入
     */
    @Autowired
    private User user;

    /**
     * 延迟注入
     */
    @Autowired
    private ObjectProvider<User> objectProvider;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectDemo.class);
        applicationContext.refresh();

        LazyAnnotationDependencyInjectDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectDemo.class);
        System.out.println(demo.user);
        System.out.println(demo.objectProvider.getObject());
        demo.objectProvider.orderedStream().forEach(System.out::println);

        applicationContext.close();
    }

    @Bean
    @Primary
    public User user1() {
        return new User("ObjectProviderHeiku", 12);
    }


    @Bean
    public User user2() {
        return new User("ObjectProviderHeiku", 13);
    }
}
