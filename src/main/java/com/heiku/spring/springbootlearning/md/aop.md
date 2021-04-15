
# AOP

aop 实现使用过 `annotationAwareAspectJAutoProxyCreator` 这个 beanPostProcessor 实现的，在 bean 初始化之后，判断 bean 是否需要生成
代理对象。

```
AbstractAutoProxyCreator

@Override
public Object postProcessAfterInitialization(@Nullable Object bean, String beanName) {
	if (bean != null) {
		Object cacheKey = getCacheKey(bean.getClass(), beanName);
		if (this.earlyProxyReferences.remove(cacheKey) != bean) {
			return wrapIfNecessary(bean, beanName, cacheKey);
		}
	}
	return bean;
}
```

在 Spring 内部，每个 __切面__ 都会封装成一个 Advisor 对象，一个 Advisor 对象内部维护了一个通知对象（advice）和一个切点对象（pointCut）。  
如果对应的 bean 是属于 adviceBean（@Aspect），那么将通过 proxyFactory 进行 proxy 代理类的创建，如果目标类没有任何接口，那么将采用 cglib 的
方式生成代理（ObjenesisCglibAopProxy），否则采用 jdk invocationHandler（JdkDynamicAopProxy）