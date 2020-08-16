package com.heiku.spring.springbootlearning.lifecycle;

import com.heiku.spring.springbootlearning.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ObjectUtils;

/**
 * Bean 销毁生命周期
 *
 * @author Heiku
 * @date 2020/8/16
 **/
public class BeanDestructionLifecycleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanDestructionLifecycleDemo.class);
        DefaultListableBeanFactory beanFactory = applicationContext.getDefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanDestructionLifecycleDemo.MyDestructionAwareBeanPostProcessor());
        applicationContext.refresh();

        User user = beanFactory.getBean(User.class);
        System.out.printf("beanFactory get bean: user= %s\n", user.toString());

        // 执行 Bean 销毁
        // Bean 销毁并不意味着被 GC 回收
        beanFactory.destroyBean(user);

        applicationContext.close();
    }

    static class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
        @Override
        public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "user") && User.class.equals(bean.getClass())) {
                User user = (User) bean;
                System.out.printf("beanPostProcessor before destruction: user= %s\n", user.toString());
            }
        }
    }

    @Bean(destroyMethod = "customDestroyMethod")
    public User user() {
        return new User("Heiku", 12);
    }
}
