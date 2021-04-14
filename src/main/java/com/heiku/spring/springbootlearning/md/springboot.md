

## SpringBoot StartUp



SpringBoot 启动大致分为两部分，1.  构建 SpringApplication 2. run()

这里主要是构建 SpringApplication 对象，然后设置启动主类 mainClass，接着就是扫描 `spring.factories` 中的配置组件，比如 listeners、initializer、analyzer 等等，实例化并保存起来，在 SpringBoot 启动过程中会调用触发



### run

1. 创建配置环境 ConfigurationEnvironment

2. 构建 applicationContext，根据 webApplicationType 创建，分为了 Servelt、Reactive、None（默认为 AnnotationConfigApplicationContext）

   在实例化 applicationContext 的时候，会通过 AnnotationConfigUtils 注册几个重要的 postProcessor 到 beanFactory 容器中，

   包括了 ConfigurationClassPostProcessor（用于扫描启动配置，注册所有的 beanDefinition）、AutowiredAnnotationBeanPostProcessor、CommonAnnotationBeanPostProcessor （后面两个用于处理 bean 实例化过程中的填充操作）等等。

3. prepareContext() 准备上下文，启动 initializer，同时加载配置源 sources 类

4. refreshContext() Spring 容器正式启动

5. afterRefresh() 结束后启动 runners，包括了 ApplicationRunners、CommandLineRunners



### refresh

1. prepareBeanFactory() 

   向 beanFactory 注册特殊的 bean 实例，比如 beanFactory、resourceLoader、applicationContext、applicationEventPublisher 等，同时忽略部分特殊的 bean 接口，比如 aware 回调



2. invokeBeanFactoryPostProcessor()

   调用所有的 beanFactoryProcessor，其中要特别注意的是会调用 ConfigurationClassPostProcessor 这个 beanFactoryPostProcessor，通过 ConfigurationClassParser 对配置项进行扫描，然后将定义的 beanDefinition 添加到容器中。



3. registerBeanPostProcessor()

   除了构建 applicationContext 默认添加的两个 beanPostProcessor（autowireAnnotationBeanPostProcessor、commonAnnotationBeanPostProcessor）之外，对上一步扫描完 beanDefinition 中  beanPostProcessor 进行实例化，并添加到容器中，方便后面实例化 bean 的时候进行调用。



 4. finishBeanFactoryInitialization()

    通过调用 beanFactory.preInstantiateSingletons() 实例化所有的非 lazy-init 的实例