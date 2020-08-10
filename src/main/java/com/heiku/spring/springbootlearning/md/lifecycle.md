
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