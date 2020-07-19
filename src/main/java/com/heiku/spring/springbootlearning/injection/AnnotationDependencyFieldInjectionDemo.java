package com.heiku.spring.springbootlearning.injection;

import com.heiku.spring.springbootlearning.entity.User;
import com.heiku.spring.springbootlearning.entity.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 字段注入
 *
 * @author Heiku
 * @date 2020/7/19
 **/
public class AnnotationDependencyFieldInjectionDemo {

    /**
     * @Autowired 会自动忽略 static 字段，被 static 标注会忽略，导致 null
     */
    @Autowired
    protected UserHolder userHolder;

    /**
     * 和 @Autowired 一样，都是通过 byType 进行注入
     */
    @Resource
    protected UserHolder userHolder2;


    /**
     * 通过方法注入的方式，将引入bean对象复制到当前对象的属性中
     */
    private UserHolder userHolder3;

    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder3 = userHolder;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);
        applicationContext.refresh();

        AnnotationDependencyFieldInjectionDemo demo = (AnnotationDependencyFieldInjectionDemo) applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);
        // @Autowired inject
        System.out.println(demo.userHolder);
        System.out.println(demo.userHolder2);
        System.out.println(demo.userHolder3);
        System.out.println(demo.userHolder2 == demo.userHolder);        // true

        applicationContext.close();
    }


    @Bean
    public User user() {
        return new User("Heiku", 12);
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
