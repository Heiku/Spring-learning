package com.heiku.spring.springbootlearning.component.xmlschema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @Author: Heiku
 * @Date: 2019/12/3
 */
public class HeikuBeanDefinitionParser implements BeanDefinitionParser {

    private final Class<?> beanClass;

    public HeikuBeanDefinitionParser(Class<?> beanClass){
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(beanClass);
        rootBeanDefinition.setLazyInit(false);

        // 解析 element 中的元素，实际项目中更复杂，会多层数节点
        String name = element.getAttribute("name");
        rootBeanDefinition.getPropertyValues().addPropertyValue("name", name);
        parserContext.getRegistry().registerBeanDefinition(name, rootBeanDefinition);

        return rootBeanDefinition;
    }


}
