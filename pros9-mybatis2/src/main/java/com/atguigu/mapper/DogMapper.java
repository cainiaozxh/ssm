package com.atguigu.mapper;

import com.atguigu.entity.Dept;
import com.atguigu.entity.Dog;

import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.atguigu.mapper
 * @CLASSNAME: DogMapper
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/28 20:27
 * @SINCE 17.0.7
 * @DESCRIPTION: DogMapper
 */
public interface DogMapper {
    public List<Dog> findAll();

    public List<Dog> findOneToOne_1();

    /**
     * 一对一映射 关联查询
     * @return
     */
    public List<Dog> findOneToOne_2();

    /**
     * 一对一映射 子查询
     * @return
     */
    public List<Dog> findOneToOne_3();


}
