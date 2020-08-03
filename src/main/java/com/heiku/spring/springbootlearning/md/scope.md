
### Spring Bean 作用域

#### 作用域

* singleton: 默认 bean 作用域，一个 BeanFactory 有且仅有一个实例

* prototype: 原型作用域，每次依赖查找和依赖注入生成新的 Bean 对象

* request: 将 Spring Bean 存储在 ServletRequest 上下文, @RequestScope

* session: 将 Spring Bean 存储在 HttpSession 中, @SessionScope

* application: 将 Spring Bean 存储在 ServletContext 中, @ApplicationScope


#### 自定义 Bean 作用域

* 实现

    * org.springframework.beans.factory.config.Scope
    
* 注册

    * api - org.springframework.beans.factory.config.ConfigurableBeanFactory#registerScope
    
    * 配置
    
        ```
            <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer>
            <property name="scopes">
            <map>
                <entry key=""
                ...
        ```
      

##### singleton Bean 是否在应用中是唯一的？

否，singleton bean 仅在当前 Spring IoC 容器（BeanFactory） 中是单例对象  

类似，一个 static 字段是不是在 jvm 中是唯一的，否，一个 static 字段限制于 一个 classloader，jvm 中存在多个 
classloader，classloader 是相互隔离的。

