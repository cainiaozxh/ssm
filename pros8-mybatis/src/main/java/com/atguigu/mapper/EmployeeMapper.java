package com.atguigu.mapper;

import com.atguigu.entity.Employee;
import com.atguigu.service.EmployeeService;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.TestOnly;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @PACKAGE_NAME: com.atguigu.mapper
 * @CLASSNAME: EmployeeMapper
 * @AUTHOR: zhangsan
 * @DATE: 2024/3/26 12:00
 * @SINCE 17.0.7
 * @DESCRIPTION: EmployeeMapper 接口
 */
public interface EmployeeMapper {
    public List<Employee> findAll();

    public int saveEmp(Employee employee);

    Employee selectEmployeeByName(String empName);

    List<Employee> orderEmployeeByColumn(String orderName);

    Employee selectEmployeeByMap(Map<String, Object> map);

    Employee selectEmployeeByAnno(@Param("empName") String name,@Param("empSalary") double salary);

    List<Employee> selectEmployeeByEntity(Employee employee);

    /**
     * 根据id查询员工
     * @param empId
     * @return
     */
    Employee selectEmployeeById(Integer empId);

    /**
     * 动态获取主键
     * @param employee
     * @return
     */
    public int saveEmpGetId(Employee employee);

    /**
     * 修改用户
     * @param employee
     * @return
     */
    public int updateEmployee(Employee employee);

    public int deleteEmployeeById(Integer empId);

    /**
     * 根据薪资查询员工
     * @param minSalary
     * @param maxSalary
     * @return
     */
    public List<Employee> selectEmpBySalary(@Param("minSalary") double minSalary,@Param("maxSalary") double maxSalary);

    public List<Employee> selectEmpBySalary2(@Param("minSalary") double minSalary,@Param("maxSalary") double maxSalary);

    public List<Employee> selectEmpBySalary3(@Param("minSalary") double minSalary,
                                             @Param("maxSalary") double maxSalary);
    public Map<String, Object> selectEmployeeMap(Integer empId);

    public List<Map<String, Object>> selectEmployeeMapNameSalary(Integer empId);

    @MapKey("emp_id")//MapKey注解的value,这里必须是表的字段名
    public Map<Integer, Map<String, Object>> selectEmployeeMapMap(Integer empId);

    public List<Employee> selectEmployeeByLike(@Param("name") String name);

    /**
     * 批量查询,参数用数组传递
     * @param array
     * @return
     */
    public List<Employee> selectEmployees(Integer[] array);

    /**
     * 批量查询,参数使用list集合
     * @param ids
     * @return
     */
    public List<Employee> selectEmployeesList(List<Integer> ids);

    /**
     * 批量查询,参数使用map集合进行传递
     * @param map
     * @return
     */
    public List<Employee> selectEmployeesMap(Map<String, List<Integer>> map);

    /**
     * 动态sql <where><where/>
     * @param employee
     * @return
     */
    public List<Employee> selectEmployeeByWhere(Employee employee);

    /**
     * 动态sql <set></set>
     * @param employee
     * @return
     */
    public int updateEmployeeBySet(Employee employee);

    public List<Employee> selectChoose(Employee employee);

}
