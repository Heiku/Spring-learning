package com.heiku.spring.springbootlearning.lookup;

import com.heiku.spring.springbootlearning.entity.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 使用 ObjectProvider 进行依赖查找（延迟查找）
 *
 * @author Heiku
 * @date 2020/7/11
 **/

// @Configuration 非必须的注解
public class ObjectProviderDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前的 ObjectProviderDemo 作为配置类 （Configuration class）
        applicationContext.register(ObjectProviderDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        // 用户行为
        // 延迟依赖查找
        lookUpByObjectProvider(applicationContext);

        // 查找缺失
        lookUpIfAvailable(applicationContext);

        // steam
        lookUpByStreamOps(applicationContext);

        // 关闭
        applicationContext.close();
    }


    // 方法名作为 Bean name
    @Bean
    @Primary
    public String helloWorld() {
        return "Hello World!";
    }

    @Bean
    public String second() {
        return "second";
    }

    /**
     * 通过 ObjectProvider 实现延迟查找
     *
     * @param applicationContext
     */
    private static void lookUpByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

    /**
     * 查找缺失
     *
     * @param applicationContext
     */
    private static void lookUpIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        User user = objectProvider.getIfAvailable(User::new);
        System.out.println("当前 User 对象: " + user);
    }


    /**
     * stream 流失缺失查找
     *
     * @param applicationContext
     */
    private static void lookUpByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        objectProvider.stream().forEach(System.out::println);
    }
}
