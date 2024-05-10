package com.atguigu.converter;

import com.atguigu.entity.Address;
import org.springframework.core.convert.converter.Converter;

/**
 * @PACKAGE_NAME: com.atguigu.converter
 * @CLASSNAME: AddressConverter
 * @AUTHOR: zhangsan
 * @DATE: 2024/2/10 11:32
 * @SINCE 17.0.7
 * @DESCRIPTION: AddressConverter
 */
public class AddressConverter implements Converter<String, Address> {
    //河北省,张家口市,崇礼县
    @Override
    public Address convert(@org.jetbrains.annotations.NotNull String value) {
        String[] split = value.split(",");
        Address address = new Address();
        address.setProvince(split[0]);
        address.setCity(split[1]);
        address.setCounty(split[2]);
        return address;
    }

}
