package com.heiku.spring.springbootlearning.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author Heiku
 * @date 2020/7/26
 **/
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ScopeEntity implements BeanNameAware {

    @NonNull
    private long id;

    @NonNull
    private String name;

    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

/*    @PostConstruct
    public void init() {
        System.out.println(this.beanName + " init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(this.beanName + " destroy");
    }*/
}
