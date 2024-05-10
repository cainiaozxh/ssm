package com.atguigu.converter;

import com.atguigu.entity.Address;
import org.springframework.core.convert.converter.Converter;

/**
 * @PACKAGE_NAME: com.atguigu.converter
 * @CLASSNAME: AddressConverter02
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/23 21:04
 * @SINCE 17.0.7
 * @DESCRIPTION: AddressConverter02 类型转换器
 */
public class AddressConverter02 implements Converter<String, Address>{
    /**
     * 河北省,张家口市,崇礼县
     * @param source
     * @return
     */
    @Override
    public Address convert(String source) {
        String[] split = source.split(",");
        Address address = new Address();
        address.setProvince(split[0].trim());
        address.setCity(split[1].trim());
        address.setCounty(split[2].trim());
        return address;
    }

    @Override
    public <U> Converter<String, U> andThen(Converter<? super Address, ? extends U> after) {
        return null;
    }
}
