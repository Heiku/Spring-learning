
### BeanDefinition

BeanDefinition 是 Spring Framework 中定义 bean 配置元信息的接口，包括：

* Bean 的类名

* Bean 行为配置元素，如作用域(scope)、自动绑定模式(byName/byType)、生命周期回调(init-method) 配合着延迟模式(lazy init)等

* 其他 Bean 引用，又可称作合作者 (Collaborators) 或者依赖 (Dependencies)

* 配置设置，比如 Bean 属性（Properties）


#### 构建

* BeanDefinitionBuilder

* AbstractBeanDefinition 以及派生类