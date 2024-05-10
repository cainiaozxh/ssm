package com.atguigu.convertor;

import com.atguigu.entity.Address;
import org.springframework.core.convert.converter.Converter;

/**
 * @PACKAGE_NAME: com.atguigu.convertor
 * @CLASSNAME: AddressConvertor
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/28 15:51
 * @SINCE 17.0.7
 * @DESCRIPTION: AddressConvertor 地址转换器
 */
public class AddressConvertor implements Converter<String, Object> {
    @Override
    public Object convert(String value) {
        String[] split = value.split(",");
        Address address = new Address();
        return address;
    }

    @Override
    public <U> Converter<String, U> andThen(Converter<? super Object, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
