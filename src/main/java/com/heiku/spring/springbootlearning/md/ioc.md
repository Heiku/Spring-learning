
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