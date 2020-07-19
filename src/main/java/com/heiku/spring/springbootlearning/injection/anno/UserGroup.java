package com.heiku.spring.springbootlearning.injection.anno;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * userGroup extends @Qualifier
 *
 * @author Heiku
 * @date 2020/7/19
 **/
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier  // extend qualifier
public @interface UserGroup {
}
