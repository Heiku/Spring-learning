package com.heiku.spring.springbootlearning.injection.resolve;

import com.heiku.spring.springbootlearning.entity.User;
import com.heiku.spring.springbootlearning.injection.anno.InjectedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 注解驱动的依赖注入过程
 *
 * @author Heiku
 * @date 2020/7/21
 **/
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    private User user;  // DependencyDescriptor ->
    // (required = true) 实时注入 + 类型依赖查找（User.class） + 字段名称 ("user")

    @InjectedUser
    private User user1;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);
        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        System.out.println(demo.user1);

        applicationContext.close();
    }

    /**
     * 自定义注解的处理器（利用现有的实现 AutowiredAnnotationBeanPostProcessor）
     * 修改指定注入的注解 InjectedUser.class
     * <p>
     * 声明为 static 表示，该 beanPostProcessor 脱离 demo 这个bean，会被提前声明初始化
     * 上下文 refresh 的时候，会调用 registerBeanPostProcessors 注册各 beanPostProcessors，
     * 由于此时当前的 demo bean 并没有被初始化，所以无法注册自定义的 beanPostProcessor
     * <p>
     * 所以在实际注入中 AutowireAnnotationBeanPostProcessor 中并不认识 @InjectedUser，导致属性无法注入，null
     *
     * @return
     */
/*    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)    // 这样会覆盖掉原来的 beanPostProcessor
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        Set<Class<? extends Annotation>> set = new HashSet<>(Arrays.asList(InjectedUser.class, Autowired.class));
        beanPostProcessor.setAutowiredAnnotationTypes(set);
        return beanPostProcessor;
    }*/
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }

    @Bean
    public User user() {
        return new User("Heiku", 12);
    }
}
