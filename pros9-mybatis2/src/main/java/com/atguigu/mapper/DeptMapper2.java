package com.atguigu.mapper;

import com.atguigu.entity.Dept;

import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.mapper
 * @CLASSNAME: DeptMapper2
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/30 7:39
 * @SINCE 17.0.7
 * @DESCRIPTION: DeptMapper2
 */
public interface DeptMapper2 {
    /**
     * 查询所有部门
     * @return
     */
    public List<Dept> findAllDept();

    public List<Dept> findAllDept_1();

    public List<Dept> findAllDept_2();
}
