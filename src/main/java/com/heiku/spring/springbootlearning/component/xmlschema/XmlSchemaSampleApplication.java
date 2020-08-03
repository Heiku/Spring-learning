package com.heiku.spring.springbootlearning.component.xmlschema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author: Heiku
 * @Date: 2019/12/3
 */

@SpringBootApplication
@ImportResource(locations = {"classpath:heiku.xml"})
public class XmlSchemaSampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(XmlSchemaSampleApplication.class, args);
        ServiceBean serviceBean = applicationContext.getBean(ServiceBean.class);
        System.out.println(serviceBean.getName());

        ApplicationConfig configBean = applicationContext.getBean(ApplicationConfig.class);
        System.out.println(configBean.getName());

    }
}
