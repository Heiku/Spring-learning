package com.heiku.spring.springbootlearning.injection.anno;

import java.lang.annotation.*;

/**
 * @author Heiku
 * @date 2020/7/22
 **/
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
