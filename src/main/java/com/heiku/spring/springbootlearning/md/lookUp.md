
### 依赖查找

#### 单一类型依赖查找

* 根据 Bean 名称查找 `getBean(String Name)`

* 根据 Bean 类型查找 `getBean(Class class)`

    延迟查找
    
    * getBeanProvider(Class)
    
    * getBeanProvider(ResolvableType)
    
* 根据 Bean 名称 + 类型查找 `getBean(String, Class)`