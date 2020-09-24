
#### 查找

首先，如果我们需要通过 `beanFactory.getBean()` 的方式查找 bean，会先通过 `beanMap` 的方式去查找，如果查找不到，说明 bean 可能
没有被实例化，那么尝试通过 beanDefinition 的方式构建 bean，如果这时候查找不到对应的 beanDefinition 那么也会抛出异常。  
如果查找得到，说明有可能是 lazy 的bean, 那么通过 `DefaultListableBeanFactory#doCreateBean()` 的方式构建 bean 实例，存储并返回。


那么，问题来了，在程序启动的时候，是怎么查找到所有的 beanDefinition，因为我们上面是按照 beanDefinitionMap 的标准进行的：


#### 样式一

1

