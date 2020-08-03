package com.heiku.spring.springbootlearning.scope;

import com.heiku.spring.springbootlearning.entity.ScopeEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * ThreadLocalScope
 *
 * @author Heiku
 * @date 2020/8/1
 **/
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public ScopeEntity scopeEntity() {
        return new ScopeEntity(System.nanoTime(), "Heiku");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册自定义 scope
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });
        applicationContext.refresh();

        scopeBeanByLookup(applicationContext);

        applicationContext.close();
    }

    private static void scopeBeanByLookup(ApplicationContext applicationContext) {
        System.out.println("threadLocalScope === ");
        for (int i = 0; i < 3; i++) {
            ScopeEntity entity = (ScopeEntity) applicationContext.getBean("scopeEntity");
            System.out.println(entity);
        }
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                ScopeEntity entity = (ScopeEntity) applicationContext.getBean("scopeEntity");
                System.out.println(entity);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
