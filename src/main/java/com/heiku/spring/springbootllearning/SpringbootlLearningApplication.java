package com.heiku.spring.springbootllearning;

import com.heiku.spring.springbootllearning.post.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.ConversionService;

@SpringBootApplication
public class SpringbootlLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootlLearningApplication.class, args);
        /*Hello hello = (Hello) SpringUtil.getCtx().getBean(Hello.class);
        System.out.println(hello.toString());*/

        ConversionService service = SpringUtil.getCtx().getBean(ConversionService.class);
        System.out.println(service.convert("25", Integer.class));
    }

}
