
### 依赖来源

#### 依赖查找的来源

* 查找来源

    * Spring BeanDefinition （有生命周期、依赖查找、依赖注入）
    
        * xml: <bean name="" value=""/>
        
        * 注解: @Bean
        
        * BeanDefinitionBuilder
        
    * Spring 内建 BeanDefinition (仅能被依赖注入)
    
        * ConfigurationClassPostProcessor: 处理 Spring 配置类
        
        * AutowiredAnnotationBeanPostProcessor: 处理 @Autowired / @Value
        
        * CommonAnnotationBeanPostProcessor: 处理 JSR-250 注解 / @PostConstruct / @PreDestroy
        
        * EventListenerMethodProcessor: 处理 @EventListener Spring 时间监听方法
        
    * Spring 内建单例对象 （无生命周期、仅依赖注入查找）
    
        * Environment: 外部化配置以及 Profiles
        
        * systemProperties: Java 系统属性
        
        * systemEnvironment: 操作系统环境变量
        
        * messageSource: 国际化文案
        
        * lifecycleProcessor: lifeCycle Bean 处理器
        
        * applicationEventMulticaster: Spring 时间广播器
        

#### Spring BeanDefinition 作为依赖来源

* 元数据: BeanDefinition

* 注册: BeanDefinitionRegistry#registerBeanDefinition

    1. 在注册的时候通过一个 ConcurrentHashMap 存储 <BeanName, BeanDefinition>
    2. 同时，为了保证 BeanDefinition 定义的有序，使用了 list 按照注册过程添加进去

* 类型: 延迟和非延迟

* 顺序: Bean 声明周期顺序按照注册顺序


#### 单例对象作为依赖来源

* 来源: 外部普通 Java 对象

* 注册: SingletonBeanRegistry$registerSingleton

    一样通过 map 存储，用 LinkedHashSet 保证存储的有序

* 限制
    
    * 无生命周期管理

    * 无法实现延迟初始化 Bean

在 `doGetBean` 的时候，会优先查找对应的 singletonBean，如果查找直接返回，如果查找的是普通的 bean，则找到 beanDefinition 之后，
还会调用 bean 的生命周期，去实现 beanDefinition -> bean 的过程。


#### 非 Spring 容器管理对象作为依赖来源

* 注册: ConfigurableListableBeanFactory$registerResolvableDependency

* 限制

    * 无声明周期管理
    
    * 无法实现延迟初始化 bean
    
    * 无法通过依赖查找


#### 外部化配置作为依赖来源 （SpringBoot）

* 类型: 非常规 Spring 对象依赖来源

* 限制: 

    * 无声明周期管理
    
    * 无法实现延迟初始化 Bean
    
    * 无法通过依赖查找
    
    
##### 注入和查找的依赖来源是否相同？

否， 依赖查找的来源仅限于 Spring BeanDefinition 以及 单例对象，而依赖注入的来源还包括Resolvable Dependency 以及
@Value 所标注的外部化配置

##### 单例对象能否在 IoC 容器启动之后注册？

可以的，单例对象的注册与 BeanDefinition 不同，BeanDefinition 会被 `ConfigurableListableBeanFactory#freezeConfiguration()` 
影响，从而冻结注册，而单例对象没有这个限制