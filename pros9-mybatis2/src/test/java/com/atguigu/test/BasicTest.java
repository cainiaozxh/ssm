package com.atguigu.test;

import com.atguigu.entity.DemoUser;
import com.atguigu.mapper.DemoUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.atguigu.test
 * @CLASSNAME: BasicTest
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/29 20:33
 * @SINCE 17.0.7
 * @DESCRIPTION: BasicTest
 */
@SpringJUnitConfig(locations = {"classpath:spring-persist.xml"})
public class BasicTest {
    @Resource
    private DemoUserMapper demoUserMapper;

    @Test
    public void insert1() {
        DemoUser user = new DemoUser(null, "斗战胜佛", 1000, "男");
        int i = demoUserMapper.insertGetKey(user);
        System.out.println("i = " + i);
        System.out.println("user = " + user);
    }

    @Test
    public void selectById1() {
        Integer id = 138;
        DemoUser demoUser = demoUserMapper.findById(id);
        demoUserMapper.findById(id);
        demoUserMapper.findById(id);
        //System.out.println("demoUser = " + demoUser);
    }

    @Test
    public void updateDemoUser() {
        DemoUser updatedDemoUser = new DemoUser(6168, "乐山大佛", 2000, "女");
        int i = demoUserMapper.updateDemoUser(updatedDemoUser);
        System.out.println("i = " + i);
    }

    @Test
    public void selectByAge1() {
        List<DemoUser> demoUsers = demoUserMapper.selectByAge(1000, 1500);
        demoUsers.forEach(System.out::println);
    }

    @Test
    public void selectByIdMap() {
        Integer id = 138;
        Map<String, Object> map = demoUserMapper.selectByIdMap(id);
        System.out.println(map);
    }

    @Test
    public void selectListMap() {
        List<Map<String, Object>> maps = demoUserMapper.selectListMap();
        maps.forEach(System.out::println);
    }

    @Test
    public void selectMapMap() {
        Map<Integer, Map<String, Object>> integerMapMap = demoUserMapper.selectMapMap();
        integerMapMap.forEach((key, value) -> {
            System.out.println(key + "=" + value);
        });
    }

    @Test
    public void selectLike() {
        List<DemoUser> demoUsers = demoUserMapper.selectLike("佛");
        demoUsers.forEach(System.out::println);
    }

    @Test
    public void selectInArray() {
        Integer[] ids = {2, 4, 5, 6, 7};
        List<DemoUser> demoUsers = demoUserMapper.selectInArray(ids);
        demoUsers.forEach(System.out::println);

        System.out.println("=========1==========");
        List<Integer> list = Arrays.asList(ids);
        List<DemoUser> demoUsers1 = demoUserMapper.selectInList(list);
        demoUsers1.forEach(System.out::println);

        System.out.println("=========2==========");
        //利用map集合封装数据
        Map<String, List<Integer>> mapIds = new HashMap<>();
        mapIds.put("ids123", list);
        List<DemoUser> demoUsers2 = demoUserMapper.selectInMap(mapIds);
        demoUsers2.forEach(System.out::println);
    }

    @Test
    public void dynamicWhere() {
        DemoUser user1 = new DemoUser(null, "乐山大佛", null, null);
        DemoUser user2 = new DemoUser(null, null, null, "女");
        List<DemoUser> demoUsers1 = demoUserMapper.selectDynamicWhere(user1);
        demoUsers1.forEach(System.out::println);
        System.out.println("========================");
        List<DemoUser> demoUsers2 = demoUserMapper.selectDynamicWhere(user2);
        demoUsers2.forEach(System.out::println);
    }

    @Test
    public void dynamicSet() {
        DemoUser user = new DemoUser(6168, "达摩老祖", 18, "男");
        int i = demoUserMapper.updateDynamicSet(user);
        System.out.println("i = " + i);
    }

    @Test
    public void selectChoose() {
        DemoUser user = new DemoUser(6168, null, null, null);
        List<DemoUser> demoUsers = demoUserMapper.selectChoose(user);
        demoUsers.forEach(System.out::println);
        System.out.println("========1========");
        DemoUser user1 = new DemoUser(null, null, null, "男");
        List<DemoUser> demoUsers1 = demoUserMapper.selectChoose(user1);
        demoUsers1.forEach(System.out::println);

    }


}
