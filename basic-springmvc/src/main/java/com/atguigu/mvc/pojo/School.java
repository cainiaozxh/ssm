package com.atguigu.mvc.pojo;

import org.springframework.core.style.ToStringCreator;

/**
 * @PACKAGE_NAME: com.atguigu.mvc.pojo
 * @CLASSNAME: School
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/25 20:46
 * @SINCE 17.0.7
 * @DESCRIPTION: School实体类
 */
public class School {
    private String schoolNo;

    private String schoolName;

    public School() {
    }

    public School(String schoolNo, String schoolName) {
        this.schoolNo = schoolNo;
        this.schoolName = schoolName;
    }

    public String getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(String schoolNo) {
        this.schoolNo = schoolNo;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolNo='" + schoolNo + '\'' +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
