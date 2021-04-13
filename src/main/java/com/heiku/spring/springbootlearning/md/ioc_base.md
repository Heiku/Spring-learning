
### ioc

#### ioc 的职责

* 依赖处理
    * 依赖查找（jndi lookup，主动查找的形式，侵入业务逻辑，需要依赖API，JNDI API、Servlet API等）
    * 依赖注入（spring: 通过容器进行属性的注入工作，非侵入性）

* 生命周期管理
    * 容器（自己的生命周期管理）
    * 托管的资源（JavaBeans 或其它资源）
    
#### 实现

* Java SE   
    * Java Beans
    * Java ServiceLoader SPI
    * JNDI (Java Naming and Directory interface)
* Java EE
    * EJB (Enterprise Java Beans)
    * Servlet
* Open Source
    * Guice
    * Spring FrameWork
    
    
#### 构造器注入 & setter 注入

* 构造器注入：通常用于注入的对象需要初始化情况（存在着参数过多的情况）
* setter注入：用于扩展，让对象更可配 （无法指定setter的 order，构造注入参数有序）

#### ioc

好莱坞原则："Don't call us, we call you"，经纪公司不希望演员主动找他们，而是在需要的时候主动联系演员。也就是，所有的
组件都是被动的，所有组件的初始化和调用都由容器负责。如果在代码中，控制权由应用代码移到了 ioc外部容器中控制权反转。

ioc 是控制反转，上层控制下层，而不是下层控制上层，类似于好莱坞原则，主要有依赖查找和依赖注入实现。

由容器负责控制对象的生命周期和对象间的关系

### 依赖查找

* 通过名称查找

    * 直接查找 (BeanFactory)
    
    `getBean(name) -> Bean.Id`
    
    * 延迟查找
    
    通过 ObjectFactory -> ObjectFactoryCreatingFactoryBean # targetBeanName   
    
    ObjectFactory 对象并不是直接返回了实际的 Bean，而是一个 Bean 的查找代理(ObjectFactoryCreatingFactoryBean)。  
    当得到 ObjectFactory 对象时，相当于 Bean 没有被创建，只有当 getObject() 方法时，才会触发 Bean 实例化等生命周期。

延迟依赖查找主要用于获取 BeanFactory 后，不马上获取相关的 Bean，比如在 BeanFactoryPostProcessor 
接口中获取 ConfigurableListableBeanFactory 时，不马上获取，降低 Bean 过早初始化的情况
    
* 通过类型查找

    * 单个 Bean 类型查找
    
    `getBean(Class<T>)` 通过Java5之后支持泛型增加的
    
    * 集合 Bean 
    
    `ListableBeanFactory.getBeansOfType(Class<T>)` 返回 map[beanName]bean
    
* 通过 Java 注解查找

    * 单个 Bean 对象
    
    * 多个对象
    
    `ListableBeanFactory.getBeanWithAnnotation(AnnotationClass<T>)`


### 依赖注入

* 根据 Bean 名称注入

`通过 beans schema 的 <property></property> 方式注入`

* 根据 Bean 类型注入

`通过 <bean autowire="byType"> 的方式，自动绑定定义 bean class 的类型`


### ioc 依赖来源

* 自定义 bean

```
@Bean
public class User{}
```

* 容器内建 Bean 对象

```
@Bean
public class User{
    private BeanFactory beanFactory;
}
```

* 容器内建依赖 

内部容器初始化的 bean 对象，例如 `Environment, ClassXmlAppplicationContext...`

### ioc 配置

* Bean 定义配置

    * 基于 XML 文件
    * 基于 Properties 文件
    * 基于 Java 注解
    * 基于 Java API 

* ioc 容器配置

    * 基于 XML 文件
    * 基于 Java 注解
    * 基于 Java API

* 外部化属性配置

    * 基于 Java 注解
    
    `@Value("host.name"")`


#### beanFactory & factoryBean

FactoryBean 是一种特殊的 Bean，需要注册到 IoC 容器，通过容器 getBean 获取 FactoryBean#getObject() 方法的内容，
可以解决复杂构建 bean 的情况  

而 BeanFactory 是 IoC 的底层容器， BeanFactory#getBean 则是依赖查找，如果 Bean 没有初始化，那么将从底层查找或构建

#### BeanFactory & ApplicationContext

Spring 中的容器本质上是 `BeanFactory`， 而 `ApplicationContext` 实现了 `BeanFactory`，作为超集，提供了更多的
企业级的功能。

```
ClassPathXmlApplicationContext -> AbstractRefreshableApplicationContext -> ApplicationContext -> BeanFactory

使用 applicationContext#getBean() 获取 bean 的过程：

class AbstractRefreshableApplicationContext{
    // 这里将 BeanFactory 组合进来，直接通过 beanFactory 容器获取 bean
    private private DefaultListableBeanFactory beanFactory;
}
applicationContext.getBean() -> AbstractApplicationContext#getBean() -> beanFactory.getBean() 
```

为什么上面的 userRepository 中的 beanFactory 对象与 ClassPathXmlApplicationContext 不一样呢？

```
AbstractApplicationContext#prepareBeanFactory() {
    beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
    beanFactory.registerResolvableDependency(ResourceLoader.class, this);
    beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
    beanFactory.registerResolvableDependency(ApplicationContext.class, this);
}
这里定义了 beanFactory 为当前的属性 beanFactory，即AbstractRefreshableApplicationContext 中的 DefaultListableBeanFactory
所以判断的时候 DefaultListableBeanFactory != ClassPathXmlApplicationContext
```

ApplicationContext 除了 IOC 角色之外，还包括：

* 面向切面 (AOP)
* 配置元数据 (Configuration Metadata)
* 资源管理 (Resources)
* 事件 (Events)
* 国际化 (i18n)
* 注解 (Annotations)
* Environment 抽象 (Environment Abstract)

##### 构造

* BeanFactory

beanFactory -> reader -> resource

```
new DefaultListableBeanFactory()
new XmlBeanDefinitionReader(beanRegistry -> beanFactory 就是一个注册中心)
reader.loadBeanDefinition(Reosurce) 
```

* ApplicationContext

application -> register() -> refresh() 

```
@Configuration
new AnnotationConfigApplicationContext()
applicationContext.regsiter(this.class -> 这里指定配置类 Configurationc.class)
applicationContext.refresh() -> 启动应用上下文

```
