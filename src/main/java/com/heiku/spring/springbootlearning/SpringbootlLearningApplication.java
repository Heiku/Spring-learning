package com.heiku.spring.springbootlearning;

import com.heiku.spring.springbootlearning.beanDefinition.BeanDefinitionDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *  CommandLineRunner 是在 SpringBoot 启动成功后运行的runnable，可以获取配置的参数值进行调试
 */
@SpringBootApplication
public class SpringbootlLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootlLearningApplication.class, args);
        /*Hello hello = (Hello) SpringUtil.getCtx().getBean(Hello.class);
        System.out.println(hello.toString());*/

       /* ConversionService service = SpringUtil.getCtx().getBean(ConversionService.class);
        System.out.println(service.convert("25", Integer.class));*/
       /* BeanDefinitionDemo demo = (BeanDefinitionDemo) SpringUtil.getCtx().getBean("beanDefinitionDemo");
        demo.run();*/
    }

/*    @Override
    public void run(String... args) throws Exception {
        // RootBeanDefinition
        rootBeanDefinition();

        // ChildBeanDefinition
        childBeanDefinition();

        // GenericBeanDefinition
        genericBeanDefinition();
    }*/




}
