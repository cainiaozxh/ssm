package com.atguigu.mapper;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.atguigu.entity.DemoUser;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.atguigu.mapper
 * @CLASSNAME: DemoUserMapper
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/29 19:49
 * @SINCE 17.0.7
 * @DESCRIPTION: DemoUserMapper 映射
 */
public interface DemoUserMapper {
    /**
     * 新增,可以获取到主键
     * @param demoUser
     * @return
     */
    public int insertGetKey(DemoUser demoUser);

    /**
     * 查所有
     * @return
     */
    public List<DemoUser> findAll();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public DemoUser findById(@Param("id") Integer id);

    /**
     * 修改操作
     * @param demoUser
     * @return
     */
    public int updateDemoUser(DemoUser demoUser);

    /**
     * 查询age范围内的DemoUser
     * @param minAge
     * @param maxAge
     * @return
     */
    public List<DemoUser> selectByAge(@Param("minAge") Integer minAge,
                                      @Param("maxAge") Integer maxAge);

    public Map<String, Object> selectByIdMap(@Param("id") Integer id);

    public List<Map<String, Object>> selectListMap();

    /**
     * map集合操作时,必须使用主键,指定了字段(表的字段)充当主键
     * @return
     */
    @MapKey("id")
    public Map<Integer,Map<String, Object>> selectMapMap();

    public List<DemoUser> selectLike(@Param("likename") String likename);

    /**
     * 批量查询 参数使用数组 <foreach></foreach>标签
     * @param ids
     * @return
     */
    public List<DemoUser> selectInArray(Integer[] ids);

    /**
     * 批量查询 参数使用list集合 <foreach></foreach>标签
     * @param ids
     * @return
     */
    public List<DemoUser> selectInList(List<Integer> ids);

    public List<DemoUser> selectInMap(Map<String, List<Integer>> ids);

    /**
     * 动态sql <where><where/> 去除多余的and or where
     * @param demoUser
     * @return
     */
    public List<DemoUser> selectDynamicWhere(DemoUser demoUser);

    /**
     * 动态sql <set><set/>
     * @param demoUser
     * @return
     */
    public int updateDynamicSet(DemoUser demoUser);

    /**
     * 分支结构<choose><choose/>
     * @param demoUser
     * @return
     */
    public List<DemoUser> selectChoose(DemoUser demoUser);


}
