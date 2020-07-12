package com.heiku.spring.springbootlearning.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * HierarchicalBeanFactory (层级 BeanFactory)
 *
 * @author Heiku
 * @date 2020/7/11
 **/
public class HierarchicalBeanFactoryDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalBeanFactoryDemo.class);
        applicationContext.refresh();

        // HierarchicalBeanFactory -> ConfigurableBeanFactory -> ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("current beanFactory's parent beanFactory: " + beanFactory.getParentBeanFactory());

        // set parent beanFactory
        HierarchicalBeanFactory parent = createParentFactory();
        beanFactory.setParentBeanFactory(parent);     // null beanDefinition
        System.out.println("current beanFactory's parent beanFactory: " + beanFactory.getParentBeanFactory());

        // find bean from BeanFactory(local or parent)
        displayContainsLocalBean(beanFactory, "carMan");
        displayContainsLocalBean(parent, "carMan");

        // find bean up
        displayContainsBean(beanFactory, "carMan");

        // 关闭
        applicationContext.close();
    }

    /**
     * 递归向上查找 (直接使用 BeanFactoryUtils 工具类，实现更优雅)
     *
     * @param beanFactory
     * @param beanName
     */
    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalBeanFactory = (HierarchicalBeanFactory) parentBeanFactory;
            if (hierarchicalBeanFactory.containsLocalBean(beanName)) {
                System.out.println("current hierarchicalBeanFactory can find the beanName");
            } else {
                System.out.println("current hierarchicalBeanFactory local has not beanName, try to find in parent beanFactory");
                displayContainsBean(hierarchicalBeanFactory, beanName);
            }
        }
    }

    /**
     * 判断当前的 beanFactory 是否拥有本地 bean，不向上查找
     *
     * @param beanFactory
     * @param beanName
     */
    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("display local beanFactory[%s] contains bean[name : %s] ：%s\n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }


    private static HierarchicalBeanFactory createParentFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(String.class);
        builder.addPropertyValue("name", "Jacky");
        beanFactory.registerBeanDefinition("carMan", builder.getBeanDefinition());
        return beanFactory;
    }
}
