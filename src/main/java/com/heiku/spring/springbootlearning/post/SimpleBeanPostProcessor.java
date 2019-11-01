package com.heiku.spring.springbootlearning.post;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * 如果这里要注入属性，不能采用实现 PriorityOrdered 的方式，因为在 registerBeanPostProcessor 时，
 * 与 AutoWireAnnotationBeanPostProcessor 属于同一层，都实现了 PriorityOrdered 接口，所以无法正常注入
 *
 * 如果这里使用了 Ordered 接口，是可以通过 @AutoWired 获取到 hello 实例的，但由于 hello 已经被提前初始化了，那么导致
 * SimpleBeanPostProcessor 中的 postProcessBeforeInitialization 及 postProcessAfterInitialization
 *
 * @Author: Heiku
 * @Date: 2019/10/24
 */

@Component
public class SimpleBeanPostProcessor implements BeanPostProcessor, PriorityOrdered {
    @Autowired
    private Hello hello;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Hello) {
            System.out.println("BeanPostProcessor post before init");

            // 如果实现了 Ordered 接口，因为 hello 对象的提前实例化初始化，导致将不会被 SimpleBeanPostProcessor 处理
             System.out.println(hello);
            return bean;
        }

        // 如果实现了 Ordered 接口，那么对于其他的 bean 还是会使用这个 SimpleBeanPostProcessor
        //System.out.println(bean);
        return bean;
    }

/*    @PostConstruct
    public void sayHello(){
        System.out.println(hello);
    }*/

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Hello) {
            System.out.println("BeanPostProcessor post after init");
            return bean;
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return PriorityOrdered.LOWEST_PRECEDENCE;
    }
}
