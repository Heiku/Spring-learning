package com.heiku.spring.springbootllearning.messagesource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @Author: Heiku
 * @Date: 2019/10/28
 */

/*
@Configuration
public class MessageConfig {

    @Bean
    MessageSource messageSource(){
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();

        // properties base name
        resourceBundleMessageSource.setBasename("message.properties");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setUseCodeAsDefaultMessage(true);

        // if not found, use base one
        //resourceBundleMessageSource.setFallbackToSystemLocale(true);

        return resourceBundleMessageSource;
    }
}
*/
