
### 依赖查找

#### 单一类型依赖查找

* 根据 Bean 名称查找 `getBean(String Name)`

* 根据 Bean 类型查找 `getBean(Class class)`

    延迟查找 (objectFactory)
    
    * getBeanProvider(Class) `reutrn ObjectProvider<T> 实现了 ObjectFactory` 实现了延迟查找
    
    * getBeanProvider(ResolvableType)
    
* 根据 Bean 名称 + 类型查找 `getBean(String, Class)`

延迟查找可以避免 A 依赖于 B 时， 如果过早地初始化 A，那么 B bean 里面的状态可能是中间态，这样容易导致一些错误。
