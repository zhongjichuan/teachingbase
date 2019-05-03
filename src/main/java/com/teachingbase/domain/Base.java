package com.teachingbase.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Base {
    private String baseId;
    private String baseName;
    private String baseDescription;
    private String baseNum;
    private String baseEnrolmentNum;
    private Date baseReportDate;
    private String baseAddress;
    private String baseStatus;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date baseCreateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date baseUpdateTime;

    private Teacher teacher;

    @Override
    public String toString() {
        return "Base{" +
                "baseId='" + baseId + '\'' +
                ", baseName='" + baseName + '\'' +
                ", baseDescription='" + baseDescription + '\'' +
                ", baseNum='" + baseNum + '\'' +
                ", baseEnrolmentNum='" + baseEnrolmentNum + '\'' +
                ", baseReportDate=" + baseReportDate +
                ", baseAddress='" + baseAddress + '\'' +
                ", baseStatus='" + baseStatus + '\'' +
                ", baseCreateTime=" + baseCreateTime +
                ", baseUpdateTime=" + baseUpdateTime +
                ", teacher=" + teacher +
                '}';
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getBaseDescription() {
        return baseDescription;
    }

    public void setBaseDescription(String baseDescription) {
        this.baseDescription = baseDescription;
    }

    public String getBaseNum() {
        return baseNum;
    }

    public void setBaseNum(String baseNum) {
        this.baseNum = baseNum;
    }

    public String getBaseEnrolmentNum() {
        return baseEnrolmentNum;
    }

    public void setBaseEnrolmentNum(String baseEnrolmentNum) {
        this.baseEnrolmentNum = baseEnrolmentNum;
    }

    public Date getBaseReportDate() {
        return baseReportDate;
    }

    public void setBaseReportDate(Date baseReportDate) {
        this.baseReportDate = baseReportDate;
    }

    public String getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress;
    }

    public String getBaseStatus() {
        return baseStatus;
    }

    public void setBaseStatus(String baseStatus) {
        this.baseStatus = baseStatus;
    }

    public Date getBaseCreateTime() {
        return baseCreateTime;
    }

    public void setBaseCreateTime(Date baseCreateTime) {
        this.baseCreateTime = baseCreateTime;
    }

    public Date getBaseUpdateTime() {
        return baseUpdateTime;
    }

    public void setBaseUpdateTime(Date baseUpdateTime) {
        this.baseUpdateTime = baseUpdateTime;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
