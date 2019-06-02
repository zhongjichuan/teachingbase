package com.teachingbase.domain;

public class ClassStudent {

    private String classId;
    private String studentId;
    private String collegeId;
    private String className;

    @Override
    public String toString() {
        return "ClassStudent{" +
                "classId='" + classId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", collegeId='" + collegeId + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
