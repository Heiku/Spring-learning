package com.heiku.spring.springbootlearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user entity
 *
 * @author Heiku
 * @date 2020/7/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private int age;
}