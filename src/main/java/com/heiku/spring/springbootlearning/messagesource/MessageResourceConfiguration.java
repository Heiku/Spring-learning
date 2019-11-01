package com.heiku.spring.springbootlearning.messagesource;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;
/**
 * @Author: Heiku
 * @Date: 2019/10/28
 */

@Component
public class MessageResourceConfiguration implements MessageSourceAware {

    private MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
