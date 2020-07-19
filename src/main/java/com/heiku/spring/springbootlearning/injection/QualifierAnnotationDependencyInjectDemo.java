package com.heiku.spring.springbootlearning.injection;

import com.heiku.spring.springbootlearning.entity.User;
import com.heiku.spring.springbootlearning.injection.anno.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Collection;

/**
 * @qualifier 限定注解
 *
 * @author Heiku
 * @date 2020/7/19
 **/
public class QualifierAnnotationDependencyInjectDemo {

    @Autowired
    private User user1;

    @Autowired
    @Qualifier("user2")     // qualifier user2
    private User user2;

    @Autowired
    private Collection<User> allUsers;      // 4 beans

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;    // 2 beans = user3 + user4 + user5 + user6

    @Autowired
    @UserGroup
    private Collection<User> userGroupsUsers;  // 2 beans = user5 + user6

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectDemo.class);
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectDemo demo = (QualifierAnnotationDependencyInjectDemo)
                applicationContext.getBean(QualifierAnnotationDependencyInjectDemo.class);
        User user1 = demo.user1;
        System.out.println(user1);

        User user2 = demo.user2;
        System.out.println(user2);

        // 分组
        System.out.println(demo.allUsers);
        System.out.println(demo.qualifierUsers);
        System.out.println(demo.userGroupsUsers);

        applicationContext.close();
    }

    @Bean
    @Primary
    public User user1() {
        return new User("Heiku", 1);
    }

    @Bean
    public User user2() {
        return new User("Heiku2", 2);
    }

    /**
     * @Qualifier 进行分组
     */
    @Bean
    @Qualifier
    public User user3() {
        return new User("qualiferHeiku", 3);
    }

    @Bean
    @Qualifier
    public User user4() {
        return new User("qualifierHeiku", 4);
    }


    /**
     * @UserGroup extends @Qualifier 扩展进行子分组
     */
    @Bean
    @UserGroup
    public User user5() {
        return new User("userGroupHeiku", 5);
    }

    @Bean
    @UserGroup
    public User user6() {
        return new User("userGroupHeiku", 6);
    }
}
