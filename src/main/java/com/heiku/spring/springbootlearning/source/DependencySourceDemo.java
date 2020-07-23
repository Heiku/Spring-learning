package com.heiku.spring.springbootlearning.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源
 *
 * @author Heiku
 * @date 2020/7/22
 **/
public class DependencySourceDemo {

    // 内置的四个固定依赖，在 refresh -> prepareBeanFactory 的时候进行注入
    // 注入在 postProcessProperties 方法执行， 早于 setter 注入，也早于 @PostConstruct
    // AbstractApplicationContext$prepareBeanFactory

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() {
        System.out.println("== later ==");
        System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory() " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext " + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext " + (applicationEventPublisher == applicationContext));
    }

    /**
     * 这四个对象并非 Spring 容器管理的对象，所以在注入的时候做了特殊处理，
     * prepareBeanFactory, 在 注册的时候提前缓存在 resolvableDependenciesMap 中，
     * 在我们通过 @Autowired 注入的时候，会从缓存的 map 中查找。
     */
    @PostConstruct
    public void initByLookUp() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型 " + beanType.getName() + " 无法在 beanFactory 中查找！");
        }
        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);
        applicationContext.refresh();

        applicationContext.close();
    }
}
