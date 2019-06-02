package com.teachingbase.domain;

import java.util.List;

public class College {
   private String collegeId;
   private String collegeName;

   private List classList;

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public List getClassList() {
        return classList;
    }

    public void setClassList(List classList) {
        this.classList = classList;
    }
}
