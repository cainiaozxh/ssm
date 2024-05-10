package com.atguigu.mvc.pojo;

/**
 * @PACKAGE_NAME: com.atguigu.mvc.pojo
 * @CLASSNAME: Tiger
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/26 10:28
 * @SINCE 17.0.7
 * @DESCRIPTION: Tiger
 */
public class Tiger {
    private Integer tigerId;

    private String tigerName;

    private Double tigerSalary;

    public Tiger() {
    }

    public Tiger(Integer tigerId, String tigerName, Double tigerSalary) {
        this.tigerId = tigerId;
        this.tigerName = tigerName;
        this.tigerSalary = tigerSalary;
    }

    public Integer getTigerId() {
        return tigerId;
    }

    public void setTigerId(Integer tigerId) {
        this.tigerId = tigerId;
    }

    public String getTigerName() {
        return tigerName;
    }

    public void setTigerName(String tigerName) {
        this.tigerName = tigerName;
    }

    public Double getTigerSalary() {
        return tigerSalary;
    }

    public void setTigerSalary(Double tigerSalary) {
        this.tigerSalary = tigerSalary;
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "tigerId=" + tigerId +
                ", tigerName='" + tigerName + '\'' +
                ", tigerSalary='" + tigerSalary + '\'' +
                '}';
    }
}
