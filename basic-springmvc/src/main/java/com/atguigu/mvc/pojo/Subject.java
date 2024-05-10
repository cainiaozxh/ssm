package com.atguigu.mvc.pojo;

/**
 * @PACKAGE_NAME: com.atguigu.mvc.pojo
 * @CLASSNAME: Subject
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/25 20:47
 * @SINCE 17.0.7
 * @DESCRIPTION: Subject实体类
 */
public class Subject {
    private String subjectId;

    private String subjectName;

    public Subject() {
    }

    public Subject(String subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
