package com.atguigu.mapper;

import com.atguigu.entity.Dept;

import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.mapper
 * @CLASSNAME: DeptMapper
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/29 15:46
 * @SINCE 17.0.7
 * @DESCRIPTION: DeptMapper
 */
public interface DeptMapper {

    /**
     * 一对多映射1
     * @return
     */
    public List<Dept> findAll2();

    /**
     * 一对多封装2
     * @return
     */
    public List<Dept> findAll2_2();

    /**
     * 一对多封装3
     * @return
     */
    public List<Dept> findAll2_3();
}
