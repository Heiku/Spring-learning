package com.heiku.spring.springbootlearning.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * User Holder
 *
 * @author Heiku
 * @date 2020/7/19
 **/
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UserHolder implements EnvironmentAware {
    @NonNull
    private User user;

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
