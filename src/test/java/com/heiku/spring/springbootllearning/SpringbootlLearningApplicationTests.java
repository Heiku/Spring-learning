package com.heiku.spring.springbootllearning;

import com.heiku.spring.springbootllearning.post.Hello;
import com.heiku.spring.springbootllearning.post.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.heiku.spring.springbootllearning.*")
public class SpringbootlLearningApplicationTests {

    @Test
    public void contextLoads() {


    }

}
