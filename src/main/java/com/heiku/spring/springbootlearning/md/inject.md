
### 依赖注入

#### 依赖注入的模式和类型

* 依赖注入类型

    * setter: <property name="user" ref="userBean"/>
    
    * 构造器: <constructor-arg name="user" ref="userBean"/>
    
    * 字段: @Autowired User user
    
    * 方法: @Autowired public void user(User user) {...}
    
    * 接口回调: class MyBean implements BeanFactoryAware{...}
    

#### 自动绑定 (Autowiring) 模式 

* Autowiring modes

    * no: 默认值，未激活，需要手动指定依赖注入对象 ref=""
    
    * byName: 根据被注入属性的名称作为 Bean 名称进行依赖查找，并将对象啊你个设置到该属性
        
        <property name="name" value="name"/>   可以直接忽略
    
    * byType: 根据被注入属性的类型作为依赖类型进行查找，并将对象设置到该属性
    
    * constructor: 特殊 byType 类型，用于构造参数
    
    
#### Autowiring 缺点

1. 如果是 byName 模式，在注入属性的时候，如果属性名称发生改变，那么就无法根据名称查找到具体的类型，需要编码的时候仔细维护
2. 如果是 byType 模式，如果在容器中同时定义了多个相同类型的 bean，那么将会导致在注入的时候造成 NoUniqueBeanException 异常，
需要手动指定 primary 优先级，带来一定的复杂度

所以建议直接采用精确匹配的方式


#### Setter 方法注入

* 手动模式

    * XML 资源配置元信息
    
    * Java 注解配置元信息
    
    * API 配置元信息

* 自动模式

    * byName
    
    * byType
    

#### 字段注入

* 手动模式

    * Java 注解配置元信息
    
        * @Autowired
        
        * @Resource
        
        * @Inject(可选)
        
#### 接口回调注入

* Aware 系列接口回调

    * 自动模式
    
        * BeanFactoryAware: 获取 IoC 容器，BeanFactory
        
        * ApplicationContextAware: 获取 Spring 应用上下文，ApplicationContext 对象
        
        * EnvironmentAware: 获取 Environment 对象
        
        * ResourceLoaderAware: 获取资源加载对象，ResourceLoader
        
        * BeanClassLoaderAware: 获取加载当前 Bean Class 的 ClassLoader
        
        * BeanNameAware: 获取当前 Bean 的名称
        
        * MessageSourceAware: 获取 MessageSource对象，用于 Spring 国际化
        
        * ApplicationEventPublisherAware: Spring 事件对象
        
        * EmbeddedValueResolverAware: StringValueResolver 占位符处理对象
        

#### 限定注入

* 使用注解 @Qualifier 限定

    * 通过 Bean 名称限定
    
    * 通过分组限定
    
* 基于注解 @Qualifier 扩展限定

    * 自定义注解 - 如 Spring Cloud 的 @LoadBalanced
    

#### 依赖处理过程

* 入口 - DefaultListableBeanFactory#resolveDependency

    1. 判断是否是 lazy，如果lazy 直接返回代理对象（并没有经过依赖查找进行字段赋值，而是等到对象执行方法的时候再进行依赖注入），否则
    调用 `doResolveDependency()`
    
    2. 判断是否是多类型的 bean `resolveMultipleBeans()` 直接返回 
    
    3. 不管是查找单bean还是多bean，都会通过 `findAutowireCandidates()` 根据类型匹配查找 bean，可以联想之前的 层次性查找（HierarchicalBeanFactory）
    
    4. 在单 bean 情况下，如果 bean 个数大于1，选择 primary bean 直接返回 `determineAutowireCandidate()`

* 依赖描述符 - DependencyDescriptor

* 自动绑定候选对象处理器 - AutowireCandidateResolver 


#### @Autowired 注入

* @Autowired 注入过程

    * 元信息解析
    
    * 依赖查找
    
    * 依赖注入（字段、方法）

1. bean 特殊实例化通过 `AbstractAutowireCapableBeanFactory#createBean()` -> doCreateBean()
2. `doCreateBean` 会调用 `applyMergedBeanDefinitionPostProcessors`，到达 AutowiredAnnotationBeanPostProcessor
3. 这里的 AutoWiredAnnotationBeanPostProcessor 实现了 MergedBeanDefinitionPostProcessor，会将多个具有继承关系的 beanDefinition 
进行属性关联（填充propertyValues）
4. `postProcessProperties` 会调用 `findAutowiringMetadata`，去查找当前 bean 对象中的元数据(InjectionMetadata)，里面包括了
待注入的 InjectedElement 信息（field、method） 等，注意：这期间 `static` 字段会被忽略
5. `InjectionMetadata#inject` 开始注入当前 bean 中的注解数据，先通过依赖查找（上面），然后通过 __反射__ 设置 InjectElement 中的属性 field
