
### ioc

#### ioc 的职责

* 依赖处理
    * 依赖查找（jndi lookup，主动查找的形式）
    * 依赖注入（spring: 通过容器进行属性的注入工作）

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