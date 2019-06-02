package com.teachingbase.service;

import com.teachingbase.domain.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    public List getTeacherListByCompany(String companyName);

    public List<Teacher> getTeacherListByParams(Map map);

    public int getMaxTeacherId();

    public boolean addTeacher(Teacher teacher);

    public boolean updateTeacher(Teacher teacher);

    public boolean delTeacher(String teacherId);

    public Teacher getTeacherDetailsByTeacherId(String teacherId);
}
