package com.heiku.spring.springbootlearning.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Heiku
 * @Date: 2019/10/29
 */

@Component
public class SimpleListener implements ApplicationListener<EventDemo> {

    @Override
    public void onApplicationEvent(EventDemo eventDemo) {
        System.out.println("receiver " + eventDemo.getMessage());
    }
}
