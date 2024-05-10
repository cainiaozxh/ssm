package com.atguigu.mvc.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.atguigu.mvc.pojo
 * @CLASSNAME: Student
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/25 20:48
 * @SINCE 17.0.7
 * @DESCRIPTION: Student实体类
 */
public class Student {
    private String stuName;

    private School school;

    private List<Subject> subjectList;

    private Subject[] subjectArray;

    private Map<String, Double> scores;

    public Student() {
    }

    public Student(String stuName, School school, List<Subject> subjectList, Subject[] subjectArray, Map<String, Double> scores) {
        this.stuName = stuName;
        this.school = school;
        this.subjectList = subjectList;
        this.subjectArray = subjectArray;
        this.scores = scores;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public Subject[] getSubjectArray() {
        return subjectArray;
    }

    public void setSubjectArray(Subject[] subjectArray) {
        this.subjectArray = subjectArray;
    }

    public Map<String, Double> getScores() {
        return scores;
    }

    public void setScores(Map<String, Double> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuName='" + stuName + '\'' +
                ", school=" + school +
                ", subjectList=" + subjectList +
                ", subjectArray=" + Arrays.toString(subjectArray) +
                ", scores=" + scores +
                '}';
    }
}
