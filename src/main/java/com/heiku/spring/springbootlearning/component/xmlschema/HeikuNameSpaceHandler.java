package com.heiku.spring.springbootlearning.component.xmlschema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 *
 *
 * @Author: Heiku
 * @Date: 2019/12/3
 */
public class HeikuNameSpaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        super.registerBeanDefinitionParser("application", new HeikuBeanDefinitionParser(ApplicationConfig.class));
        super.registerBeanDefinitionParser("service", new HeikuBeanDefinitionParser(ServiceBean.class));
    }
}
