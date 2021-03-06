
### BeanDefinition

BeanDefinition 是 Spring Framework 中定义 bean 配置元信息的接口，包括：

* Bean 的类名

* Bean 行为配置元素，如作用域(scope)、自动绑定模式(byName/byType)、生命周期回调(init-method) 配合着延迟模式(lazy init)等

* 其他 Bean 引用，又可称作合作者 (Collaborators) 或者依赖 (Dependencies)

* 配置设置，比如 Bean 属性（Properties）

#### 构建 BeanDefinition

* BeanDefinitionBuilder

```
BeanDefinitionBuilder.genericBeanDefinition(User.class)
builder.addPropertyValue(key, value)
builder.getBeanDefinition()
```

* AbstractBeanDefinition 以及派生类

```
new GenericBeanDefinition();
genericBeanDefinition.setBeanClass(User.class)
genericBeanDefinition.setPropertyValues();
```

#### 注册 BeanDefinition

* XML

`<bean name="" class="User.class"/>`

* Annotation

    * @Bean
    * @Component
    * @Import
    
* Java API

    * 命名方式
    
    `BeanDefinitionRegistry#registerBeanDefinition(String, BeanDefinition)`
    
    * 非命名方式
    
    `BeanDefinitionReaderUtils#registerWithGeneratedName(AbstractBeanDefinition, BeanDefinitionRegistry)`
    
    * 配置类方式
    
    `AnnotatedBeanDefinitionReader#register(Class)`
    
#### 实例化 Spring Bean

* Bean 实例化(Instantiation)

    * 常规方式
    
        * 构造器
        * 静态工厂
        
        `<bean id="", class="", factory-method="createUser">`
        
        * Bean 工厂方法
        
        ```
        interface UserFactory{
            default createUser(){
                return User.createUser();
            }
        }
      
        class DefaultUserFactory implements UserFactory{
        }
        
        <bean id="userFactory" class="DefaultUserFactory"/>
        <bean id="" factory-bean="userFactory" factory-method="createUser"/>
        ```
        
        * FactoryBean
        
        ```
        class UserFactoryBean implements FactoeyBean{
            getObject() -> User.createUser()...
            getObjectType -> return User.class
        }
        ```
        
    * 特殊方式
    
        * ServiceLoaderFactoryBean
        
        * AutowireCapableBeanFactory#createBean(java.lang.Class, int, boolean)
        
        * BeanDefinitionRegistry#registryBeanDefinition(String, BeanDefinition)
        
#### 初始化 Spring Bean

Bean 初始化 (Initialization)

* @PostConstruct 方法

* 实现 InitializingBean 接口的 afterPropertiesSet() 方法

* 自定义初始化方法

    * XML 配置： <bean init-method="" />
    
    * Java 注解 @Bean(initMethod = "")
    
    * Java API： AbstractBeanDefinition#setInitMethodName(string)
    
优先级如下：@PostConstruct -> InitializingBean -> init-method


#### 延迟初始化 Spring Bean

* Bean 延迟初始化 (Lazy Initialization)

    * XML: <bean lazy-init="" />
    
    * 注解: @Lazy(true)

注册的时候无差别，区别在依赖查找和依赖注入的时候  
非延迟初始化在应用上下文启动之前进行初始化(`applicationContext#refresh`)，而延迟初始化在启动后，当用到的时候
进行初始化。

#### 销毁 Spring Bean

* Bean 销毁（Destroy）

    * @PreDestroy
    
    * 实现 DisposableBean 接口的 destroy() 方法
    
    * 自定义销毁方法
        * XML: <bean destroy="destroy" .../>
        * 注解: @Bean(destroy="destroy")
        * API: AbstractBeanDefinition#setDestroyMethodName(String)
        
#### 垃圾回收 Spring Bean

* Bean 垃圾回收

    * 关闭 Spring 容器（应用上下文） `applicationContext.close()`
    
    * 执行 GC
    
    * Spring Bean 覆盖 finalize() 方法回调
    
    
