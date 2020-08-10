package com.heiku.spring.springbootlearning.lifecycle;

import com.heiku.spring.springbootlearning.entity.ScopeEntity;
import com.heiku.spring.springbootlearning.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ObjectUtils;

/**
 * @author Heiku
 * @date 2020/8/9
 **/
public class BeanInstantiationLifecycleDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInstantiationLifecycleDemo.class);
        DefaultListableBeanFactory beanFactory = applicationContext.getDefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        applicationContext.refresh();

        User user = beanFactory.getBean(User.class);
        System.out.println(user);

        ScopeEntity entity = beanFactory.getBean(ScopeEntity.class);
        System.out.println(entity);

        applicationContext.close();
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            /*if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(beanClass)) {
                return new User();
            }*/
            // postProcessBeforeInstantiation 如果实例前已经构建出对象，那么将跳过 instantiate 阶段，并跳过赋值阶段
            return null;
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("entity", beanName) && ScopeEntity.class.equals(bean.getClass())) {
                // "entity" 对象不允许属性赋值
                ScopeEntity entity = (ScopeEntity) bean;
                entity.setId(6791L);
                entity.setName("HeikuGoGo");
                return false;
            }
            return true;
        }

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            // 如果 postProcessAfterInstantiation 返回 false，那么将跳过属性的植入阶段'
            if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                MutablePropertyValues propertyValues = new MutablePropertyValues();
                propertyValues.addPropertyValue("name", "Mystic");
                propertyValues.addPropertyValue("age", 11);
                return propertyValues;
            }
            return null;
        }
    }


    @Bean
    public User user() {
        return new User("Heiku", 12);
    }

    @Bean
    public ScopeEntity entity() {
        //return new ScopeEntity(6790L, "Heiku");
        ScopeEntity entity = new ScopeEntity();
        entity.setId(6790L);
        entity.setName("Heiku");
        return entity;
    }
}
