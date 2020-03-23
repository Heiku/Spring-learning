### Spring-learning
a project recording all my spring learning note

#### 总览

当实现一个 IoC 容器时，需要通过 `ResourceLoader` 获取一个 `Resource`，其中包括了容器的配置、`Bean` 的定义信息。
接着，使用 `BeanDefinitionReader` 读取该 `Resource` 中的 `BeanDefinition` 信息。最后，把 `BeanDefinition` 保存在 
`BeanFactory` 中，容器配置完毕之后可以直接使用。

`BeanFactory` 只是实现了 `Bean` 的装配、获取，并未说明 `Bean` 的来源 也就是 `BeanDefinition` 是如何加载的。而这个将 
`BeanFactory` 和 `BeanDefinitionReader` 结合的工作就由 `ApplicationContext` 完成。

[ioc](/src/main/java/com/heiku/spring/springbootlearning/md/ioc.md)  
[schema](/src/main/java/com/heiku/spring/springbootlearning/xmlschema/xmlSchema.md)  