package com.atguigu.mapper;

import com.atguigu.entity.Dog;

import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.mapper
 * @CLASSNAME: DogMapper2
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/30 7:39
 * @SINCE 17.0.7
 * @DESCRIPTION: DogMapper2
 */
public interface DogMapper2 {

    public List<Dog> findAllDog();

    public List<Dog> findAllDogRM();

    public List<Dog> findDogOneToOne_1();

    public List<Dog> findDogOneToOne_2();

    public int saveDog(Dog dog);
}
