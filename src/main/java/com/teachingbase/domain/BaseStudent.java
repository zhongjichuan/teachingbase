package com.teachingbase.domain;

public class BaseStudent {
    public String baseId;
    public String studentId;
    public Integer reportStatus;

    @Override
    public String toString() {
        return "BaseStudent{" +
                "baseId='" + baseId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", reportStatus=" + reportStatus +
                '}';
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }
}
