
### Spring 生命周期

#### Spring Bean 元信息配置阶段

* BeanDefinition 配置

    * 面向资源
    
        * XML 配置
        
        * Properties 资源配置
        
    * 面向注解
    
    * 面向 API
    
    
#### Spring Bean 元信息解析阶段

* 面向资源 BeanDefinition 解析

    * BeanDefinitionReader
    
    * XML 解析器 - BeanDefinitionParser
    
* 面向注解 BeanDefinition 解析

    * AnnotationBeanDefinitionReader
    
    
#### Spring Bean 注册阶段

* BeanDefinition 注册接口

    * BeanDefinitionRegistry - DefaultListableBeanFactory
    
        * 使用 concurrentHashMap 记录 <beanNames, BeanDefinition>
        
        * 使用 list 记录 beanNames，保证注册的顺序
        

#### Spring BeanDefinition 合并阶段

* BeanDefinition 合并

    * 父子 BeanDefinition 合并
    
        * 当前 BeanFactory 查找
        
        * 层次性 BeanFactory 查找
    
    * BeanDefinition 
    
        * RootBeanDefinition: 不需要合并，无 parent
        
        * GenericBeanDefinition: 普通 beanDefinition
            
    * 原理
    
        * ConfigurableBeanFactory#getMergedBeanDefinition
        
            * 先判断当前 beanFactory#containsBeanDefinition() 如果在 `beanDefinitionMap` 中没找到，则在 parentBeanFactory 中查找
            
            * AbstractBeanFactory#getMergedBeanDefinition(name, beanDefinition) 中查找 mergedBeanDefinition，这里的 bd 是默认实现 GenericBeanDefinition
            
            * 查找之前会先从 `mergedBeanDefinitionMap` 缓存中查找，如果找不到，再根据 beanDefinition 属性查找
            
            * 如果当前 bd 没有 parent，直接返回 mew RootBeanDefinition(this)，否则会对 parent beanDefinition(root) -> copy(return tmp root BeanDefinition) -> add properties(return RootBeanDefinition)



#### Spring Bean Class 加载阶段

* ClassLoader 类加载
    
* Java Security 安全控制
    
* ConfigurableBeanFactory 临时 ClassLoader      
    

BeanDefinition 中存储的是 className，在 AbstractBeanFactory#resolveBeanClass 中会将 string 的 beanClass 通过当前线程的 
Thread.currentThread().getContextClassLoader(), Class.forName() 获取 class 对象，将 beanClass 转换成 Class 对象

#### Spring Bean 实例化前阶段

* 非主流生命周期- Bean 实例化阶段

    * InstantiationAwareBeanPostProcessor#postProcessorBeforeInstantiation


#### Spring Bean 实例化阶段

* 实例化方式

    * 传统实例化方式
    
        * 实例化策略 - InstantiationStrategy
        
    * 构造器依赖注入
    
    Constructor.newInstance(args...)，构造参数再按照依赖查找，进行参数的构造。
    
    构造器内部注入是按照类型注入，如果参数是 user，根据 type 找到对应的 bean type
    
AbstractAutowireCapableBeanFactory#doCreateBean


#### #### Spring Bean 实例化后阶段

* Bean 属性赋值(populate) 判断

    * InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation
    

#### Spring Bean 属性赋值前阶段

* Bean 属性值元信息 

    * PropertyValues
      
* Bean 属性赋值前回调

    * Spring 5.1 之前: InstantiationAwareBeanPostProcessor#postProcessorPropertyValues
    
    * Spring 5.1 之后: InstantiationAwareBeanPostProcessor#postProcessorProperties 
    
AbstractAutowireCapableBeanFactory#populateBean


#### Spring Bean Aware 接口回调阶段

* Spring Aware 接口

    * BeanNameAware
    
    * BeanClassLoaderAware
    
    * BeanFactoryAware
    
    * EnvironmentAware
    
    * EmbeddedValueResolverAware
    
    * ResourceLoaderAware
    
    * ApplicationEventPublisherAware
    
    * MessageSourceAware
    
    * ApplicationContextAware
    

AbstractAutowireCapableBeanFactory#initializeBean() -> invokeAwareMethods，在初始化阶段进行接口回调，设置 bean 的 BeanNameAware,
BeanClassLoaderAware, BeanFactoryAware

initializeBean() -> applyBeanPostProcessorsBeforeInitialization() -> ApplicationContextAwareProcessor#postProcessBeforeInitialization -> invokeAwareInterfaces, 
设置剩余的 EnvironmentAware...


#### Spring Bean 初始化前阶段

* 已完成

    * Bean 实例化
    
    * Bean 属性赋值
    
    * Bean Aware 接口回调
    
* 方法回调

    * BeanPostProcessor#postProcessBeforeInitialization
    
AbstractAutowireCapableBeanFactory#initializaBean -> applyBeanPostProcessorsBeforeInitialization(),  
一般我们在 beanFactory.addBeanPostProcessor() 会在 beanFactory 中的 beanPostProcessorList 中添加，
等到 processor 执行的时候一个一个执行。

postProcessAfterInitialization 类似


#### Spring Bean 初始化阶段

* Bean 初始化 (Initialization)

    * @PostConstruct 标注方法
    
        @PostConstruct 会被 CommonAnnotationBeanPostProcessor(extends InitDestroyAnnotationBeanPostProcessor) 添加扫描，最终的执行
        都是在 InitDestroyAnnotationBeanPostProcessor 中处理，InitDestroyAnnotationBeanPostProcessor 中有两个list initAnnotationType，
        destroyAnnotationType，用于存储含有 @PostConstruct、@PreDestroy 的 beanClass，所有的 beanClass 都被封装成了 lifecycleMetaData 
        存放于 lifecycleMetadataCache，可以方便地进行反射调用（方法执行或者属性注入）
        
        InitDestroyAnnotationBeanPostProcessor 本质上就是一个 beanPostProcessor，在上面的 AbstractAutowireCapableBeanFactory#initializaBean 
        执行初始化时，会调用 beanPostProcessor 的 postProcessBeforeInitialization()，而 InitDestroyAnnotationBeanPostProcessor 的 before... 
        本质上就是调用 lifecycleMetaDataCache 中含有 LiefcycleMetadata#initMethods(@PostConstruct 注解) 的对象，最后通过反射一个一个调用 
        method 对象.
    
    * 实现 InitializingBean 接口的 afterPropertiesSet() 方法
    
        AbstractAutowireCapableBeanFactory#initializaBean __实际初始化__ invokeInitMethods()，如果 bean instanceOf InitializingBean，
        那么调用 ((InitializingBean) bean).afterPropertiesSet().
    
    * 自定义初始化方法
        
        接着就是 bean 自定义的初始化方法 (xml init-method, annotation @Bean(initMethod=""))，invokeCustomInitMethod()


#### Spring Bean 初始化后阶段

* 方法回调

    * BeanPostProcessor#postProcessAfterInitalization
    

#### Spring Bean 初始化完成阶段

* 方法回调

    * Spring 4.1+: SmartInitializingSingleton#afterSingletonInstantiated
    
AbstractApplicationContext#refresh() -> finishBeanFactoryInitialization() -> beanFactory.preInstantiateSingletons() 
这时候对 beanFactory 中的所有实例进行最终的实例初始化，具体实现为 DefaultListableBeanFactory#preInstantiateSingletons()


#### Spring Bean 销毁前阶段

* 方法回调

    * DestructionAwareBeanPostProcessor#postProcessBeforeDestruction

类似与 @PostConstruct，在 CommonAnnotationBeanPostProcessor 设置销毁注解 @PreDestroy, 当 AbstractBeanFactory#destroyBean() 
主动销毁 bean 的时候，会构建 DisposableBeanAdapter#destroy 去调用具体的销毁流程。

具体调用过程就会先执行 DestructionAwareBeanPostProcessor#postProcessBeforeDerstruction(@PreDestroy), 接着是实现的接口 DisposableBean
的 destroy()，然后执行 bean 中自定义的销毁方法。