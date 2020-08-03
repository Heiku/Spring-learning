package com.heiku.spring.springbootlearning.scope;

import com.heiku.spring.springbootlearning.entity.ScopeEntity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * Bean 作用域
 * <p>
 * Spring 容器没法管理 prototype bean 的完整生命周期，也没办法记录实例的存在。
 * 销毁的回调方法将不会执行，可以利用 beanPostProcessor 进行清理工作。
 * <p>
 * <p>
 * 1.
 * singleton: 每次依赖查找 / 依赖注入 都是同一个对象
 * prototype: 每次依赖查找 / 依赖注入 都会生成新的对象
 * <p>
 * 2.
 * 如果依赖注入集合类型的对象，singleBean 和 prototypeBean 均存在一个
 * <p>
 * 3.
 * 无论是 singleton 还是 prototype 均会回调初始化方法
 * 不过仅有 singleton 会调用执行销毁方法
 *
 * @author Heiku
 * @date 2020/7/26
 **/
public class BeanScopeDemo {

    /**
     *
     */
    @Autowired
    @Qualifier("singletonEntity")
    private ScopeEntity singletonEntity;

    @Autowired
    @Qualifier("prototypeEntity")
    private ScopeEntity prototypeEntity1;
    @Autowired
    @Qualifier("prototypeEntity")
    private ScopeEntity prototypeEntity2;
    @Autowired
    @Qualifier("prototypeEntity")
    private ScopeEntity prototypeEntity3;

    /**
     * 如果依赖注入集合类型的对象，singleBean 和 prototypeBean 均存在一个
     */
    @Autowired
    private Map<String, ScopeEntity> map;

    @Bean
    public static ScopeEntity singletonEntity() {
        return new ScopeEntity(System.nanoTime(), "singletonEntity");
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static ScopeEntity prototypeEntity() {
        return new ScopeEntity(System.nanoTime(), "prototypeEntity");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);

        // 利用 beanPostProcessor 管理 prototype 的销毁
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean name: %s 在执行初始化方法后回调", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        applicationContext.refresh();


        // 依赖查找
        scopeBeanByLookup(applicationContext);

        // 依赖注入
        scopeBeanByInject(applicationContext);

        applicationContext.close();
    }


    private static void scopeBeanByLookup(ApplicationContext applicationContext) {
        System.out.println("singletonScope === ");
        for (int i = 0; i < 3; i++) {
            ScopeEntity singletonEntity = (ScopeEntity) applicationContext.getBean("singletonEntity");
            System.out.println(singletonEntity);
        }

        System.out.println("prototypeScope === ");
        for (int i = 0; i < 3; i++) {
            ScopeEntity prototypeEntity = (ScopeEntity) applicationContext.getBean("prototypeEntity");
            System.out.println(prototypeEntity);
        }
    }

    private static void scopeBeanByInject(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);

        System.out.println("beanScopeDemo.singletonEntity = " + demo.singletonEntity);
        System.out.println("beanScopeDemo.prototypeEntity1 = " + demo.prototypeEntity1);
        System.out.println("beanScopeDemo.prototypeEntity2 = " + demo.prototypeEntity2);
        System.out.println("beanScopeDemo.prototypeEntity3 = " + demo.prototypeEntity3);

        System.out.println(demo.map);
    }
}
