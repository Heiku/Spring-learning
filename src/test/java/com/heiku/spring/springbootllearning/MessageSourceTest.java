package com.heiku.spring.springbootllearning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

/**
 * @Author: Heiku
 * @Date: 2019/10/28
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootlLearningApplicationTests.class)
public class MessageSourceTest {

    @Autowired
    private MessageSource messageSource;

    @Test
    public void testBaseMessageSource(){
        String n = messageSource.getMessage("exception", null, Locale.ENGLISH);
        System.err.println("en messageSource = " + n);

    }

    @Test
    public void testEnMessageSource(){
        String n = messageSource.getMessage("exception", null, Locale.ENGLISH);
        System.err.println("en messageSource = " + n);

    }


    @Test
    public void testChMessageSource(){
        String n = messageSource.getMessage("exception", null, Locale.CHINESE);
        System.err.println("en messageSource = " + n);
    }


    @Test
    public void testJpMessageSource(){
        String n = messageSource.getMessage("exception", null, Locale.JAPAN);
        System.err.println("en messageSource = " + n);

    }
}
