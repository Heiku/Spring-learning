
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