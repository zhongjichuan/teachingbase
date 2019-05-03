package com.teachingbase.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Teacher {
    private String teacherId;
    private String teacherName;
    private String teacherPosition;
    private String phone;
    private String sex;
    private String email;
    private Integer age;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    private Base base;

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherPosition='" + teacherPosition + '\'' +
                ", teacherPhone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", base=" + base +
                '}';
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPosition() {
        return teacherPosition;
    }

    public void setTeacherPosition(String teacherPosition) {
        this.teacherPosition = teacherPosition;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String teacherPhone) {
        this.phone = teacherPhone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

}
