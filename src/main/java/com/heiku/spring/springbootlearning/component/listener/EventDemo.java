package com.heiku.spring.springbootlearning.component.listener;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 *
 * @Author: Heiku
 * @Date: 2019/10/29
 */

@Data
public class EventDemo extends ApplicationEvent {

    private String message;

    public EventDemo(Object source, String message){
        super(source);
        this.message = message;
    }
}
