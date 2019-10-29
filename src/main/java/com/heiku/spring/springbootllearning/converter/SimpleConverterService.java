package com.heiku.spring.springbootllearning.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Heiku
 * @Date: 2019/10/29
 */

/*@Component
public class SimpleConverterService implements ConversionService {

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        if (Date.class.isAssignableFrom(targetType)){
            return true;
        }
        System.out.println(targetType);
        return false;
    }

    @Override
    public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (Date.class.isAssignableFrom(targetType.getObjectType())){
            return true;
        }
        return false;
    }

    @Override
    public <T> T convert(Object o, Class<T> aClass) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String object = (String) o;
        return (T) sdf.format(object);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (String.class.isAssignableFrom(sourceType.getObjectType())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(source);
        }
        return null;
    }
}*/
