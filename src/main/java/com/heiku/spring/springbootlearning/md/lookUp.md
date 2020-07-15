
### 依赖查找

#### 依赖查找的今世前身

* 单一类型依赖查找

    * JNDI - `javax.naming.Context#lookup` (javax.naming.Name)
    
    * JavaBeans - `java.beans.beancontext.BeanContext`
    
* 集合类型依赖查找

    * java.beans.beancontext.BeanContext
    
* 层次性依赖查找

    * java.beans.beancontext.BeanContext
    


#### 单一类型依赖查找

* 根据 Bean 名称查找 `getBean(String Name)`

* 根据 Bean 类型查找 `getBean(Class class)`

    延迟查找 (objectFactory)
    
    * getBeanProvider(Class) `reutrn ObjectProvider<T> 实现了 ObjectFactory` 实现了延迟查找
    
    * getBeanProvider(ResolvableType)
    
* 根据 Bean 名称 + 类型查找 `getBean(String, Class)`

延迟查找可以避免 A 依赖于 B 时， 如果过早地初始化 A，那么 B bean 里面的状态可能是中间态，这样容易导致一些错误。


#### 集合类型依赖查找

集合类型依赖查找接口 - ListableBeanFactory

* 根据 Bean 类型查找

    * 获取同类型 Bean 名称列表
    
        * getBeanNameForType(Class) / getBeanNamesForType(ResolvableType)
        
    * 获取同类型 Bean 实例列表
        
        * getBeansOfType(Class)
        
* 通过注解类型查找

    * getBeanNamesForAnnotation(Class<? extends Annotation>)
    
    * getBeanNamesWithAnnotation(Class<? extends Annotation>)
    
    * finAnnotationOnBean(String, Class<? extends Annotation>)


`getBeansOfType()` 会提前初始化 bean，如果 bean 未创建的话会抛出 `BeansException`，建议使用 `getBeanNameForType`，对比 BeanDefinition 
中的 `getBeanClassName` 类是否相同，包括父子基础类关系，因为本质上就是使用这个方法，去避免 bean 过早初始化导致的状态的问题。  


#### 层次性依赖查找

层次性依赖查找接口 - HierarchicalBeanFactory

* 双亲 BeanFactory: getParentBeanFactory()

* 层次性查找

    * 根据 Bean 名称查找
        
        * 基于 containsLocalBean 方法实现
        
    * 根据 Bean 类型查找实例列表
    
        * 单一类型：BeanFactoryUtils#beanOfType
        
        * 集合类型: BeanFactoryUtils#beansOfTypeIncludingAncestors
        
    * 根据 Java 注解查找名称列表
    
        * BeanFactoryUtils#beanNamesForTypeIncludingAncestors
        

#### 延迟依赖查找

* Bean 延迟依赖查找接口

    * org.springframework.beans.factory.ObjectFactory
    
    * org.springframework.beans.factory.ObjectProvider
    
        * Spring 5 对 Java 8 特性扩展
            
            * 函数式接口
            
                * getIfAvailable(Supplier)
                
                * ifAvailable(Consumer)
                
            * Stream 扩展 - stream()
            

#### 内建可查找的依赖

* AbstractApplicationContext 内建可查找的依赖

    * environment
    
    * systemProperties
    
    * systemEnvironment
    
    * messageSource
    
    * lifecycleProcessor
    
    * applicationEventMulticaster
    
* 注解驱动 Spring 应用上下文内建可查找的依赖

    * ConfigurationClassPostProcessor
    
    * AutowiredAnnotationBeanPostProcessor
    
    * CommonAnnotationBeanProcessor
    
    * EventListenerMethodProcessor
    
    * DefaultEventListenerFactory
  
    * PersistenceAnnotationBeanProcessor
    

#### 依赖查找中出现的异常

* BeansException

    * NoSuchBeanDefinitionException: 当查找 bean 不存在 ioc 容器中
    
        * BeanFactory#getBean / ObjectFactory#getObject
        
    * NoUniqueBeanDefinitionException: 类型依赖查找，ioc 容器存在多个 bean 实例
        
        * BeanFactory#getBean(Class)
        
    * BeanInstantiationException: bean 对应的类型非具体类
    
    * BeanCreationException: bean 初始化过程中
    
    * BeanDefinitionStoreException: bean 配置元信息非法(xml 配置资源无法打开)