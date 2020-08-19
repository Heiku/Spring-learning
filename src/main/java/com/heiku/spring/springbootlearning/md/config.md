
#### Spring 配置元信息

* 配置元信息

    * Spring Bean 配置元信息 - BeanDefinition
    
    * Spring Bean 属性元信息 - PropertyValues
    
    * Spring 容器配置元信息
    
    * Spring 外部化配置元信息 - PropertySource
    
    * Spring Profile 元信息 - @Profile
    

#### Spring Bean 配置元信息

* Bean 配置元信息 - BeanDefinition

    * GenericBeanDefinition: 通用型 BeanDefinition
    
    * RootBeanDefinition: 无 Parent 的 BeanDefinition 或者合并后 BeanDefinition
    
    * AnnotatedBeanDefinition: 注解标注的 BeanDefinition
    
    
#### Spring Bean 属性元信息

* Bean 属性元信息 - PropertyValues

    * 可修改实现 - MutablePropertyValues
    
    * 元素成员 - PropertyValue
    
* Bean 属性上下文存储 - AttributeAccessor

* Bean 元信息元素 - BeanMetadataElement


#### Spring 容器配置元信息

* Spring XML 配置元信息 - 应用上下文相关

    * <context:annotation-config />: 激活 Spring 注解驱动
    
    * <context:component-scan />: Spring @Component 以及自定义注解扫描
    


#### 基于 Java 注解装载 Spring Bean 配置元信息

* Spring 模式注解

    * @Repository: 数据仓储模式
    
    * @Component: 通用组件模式
    
    * @Service: 服务模式
    
    * @Controller: Web 控制器模式
    
    * @Configuration: 配置模式
     
     
#### Spring Bean 配置元信息底层实现

* Spring BeanDefinition 解析与注册

    * XML 资源: XmlBeanDefinitionReader
    
    * Properties 资源: PropertiesBeanDefinitionReader
    
    * Java 注解: AnnotatedBeanDefinitionReader