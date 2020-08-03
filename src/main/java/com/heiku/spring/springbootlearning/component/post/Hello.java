package com.heiku.spring.springbootlearning.component.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author: Heiku
 * @Date: 2019/10/24
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hello implements InitializingBean {

    private String name;

    private String words;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setWords("this is change world");
    }

    public void myInitMethod(){
        this.name = "Jane";
    }
}
