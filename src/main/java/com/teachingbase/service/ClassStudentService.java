package com.teachingbase.service;

import com.teachingbase.domain.Base;
import com.teachingbase.domain.User;

import java.util.List;
import java.util.Map;

public interface ClassStudentService {

    public List<String> getClassNameByCollegeName(String collegeName);

    public List<User> getStudentsByCounselor(Map map);

    public int getCountStudent(String collegeName);

    public List<User> getStudentsBySearchParams(Map map);

    public int getCountStudentByParams(Map map);

    public Base getStudentOfBaseByBaseId(String baseId);

    public Base getStudentOfBaseByBaseIdAndPage(Map map );
}
