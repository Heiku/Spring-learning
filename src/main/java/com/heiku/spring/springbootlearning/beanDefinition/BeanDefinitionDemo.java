package com.heiku.spring.springbootlearning.beanDefinition;

import com.heiku.spring.springbootlearning.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author: Heiku
 * @Date: 2019/11/1
 */

@Component
@Slf4j
public class BeanDefinitionDemo {

    @Autowired
    private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();


//    @Autowired
//    private GenericApplicationContext ctx;

    public void run(){
        ctx.refresh();

        rootBeanDefinition();

        childBeanDefinition();

        genericBeanDefinition();
    }

    private void rootBeanDefinition(){
        RootBeanDefinition root = new RootBeanDefinition();
        root.setBeanClass(Pet.class);
        root.getPropertyValues().add("type", "cat");
        root.getPropertyValues().add("name", "Kat");

        ctx.registerBeanDefinition("rootPetCat", root);
        Pet pet = (Pet) ctx.getBean("rootPetCat");
        log.info("Parent pet cat is :{}", pet);
    }


    private void childBeanDefinition(){
        ChildBeanDefinition child = new ChildBeanDefinition("rootPetCat");
        child.getPropertyValues().add("name", "little Kat");
        ctx.registerBeanDefinition("childPetCat", child);

        Object pet = ctx.getBean("childPetCat");
        log.info("Child pet cat is :{}", pet);
    }


    private void genericBeanDefinition(){
        GenericBeanDefinition generic = new GenericBeanDefinition();
        generic.setBeanClass(Pet.class);
        generic.getPropertyValues().add("type", "cat");
        generic.getPropertyValues().add("name", "Tom");

        ctx.registerBeanDefinition("genericParentPetCat", generic);
        Pet pet = (Pet) ctx.getBean("genericParentPetCat");
        log.info("Generic Parent pet cat is :{}", pet);


        GenericBeanDefinition genericChild = new GenericBeanDefinition();
        genericChild.setParentName("genericParentPetCat");
        genericChild.getPropertyValues().add("name", "little Tom");
        ctx.registerBeanDefinition("genericChildPetCat", genericChild);
        Object gen = ctx.getBean("genericChildPetCat");
        log.info("Generic Child pet cat is :{}", gen);
    }

}
