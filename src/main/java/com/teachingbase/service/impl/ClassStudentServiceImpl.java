package com.teachingbase.service.impl;

import com.teachingbase.dao.ClassStudentMapper;
import com.teachingbase.domain.Base;
import com.teachingbase.domain.User;
import com.teachingbase.service.ClassStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassStudentServiceImpl implements ClassStudentService {

    @Autowired
    public ClassStudentMapper classStudentMapper;

    @Override
    public List<String> getClassNameByCollegeName(String collegeName) {
        return classStudentMapper.getClassNameByCollegeName(collegeName);
    }

    @Override
    public List<User> getStudentsByCounselor(Map map) {
        return classStudentMapper.getStudentsByCounselor(map);
    }

    @Override
    public int getCountStudent(String collegeName) {
        return classStudentMapper.getCountStudent(collegeName);
    }

    @Override
    public List<User> getStudentsBySearchParams(Map map) {
        return classStudentMapper.getStudentsBySearchParams(map);
    }

    @Override
    public int getCountStudentByParams(Map map) {
        return classStudentMapper.getCountStudentByParams(map);
    }

    @Override
    public Base getStudentOfBaseByBaseId(String baseId) {
        return classStudentMapper.getStudentOfBaseByBaseId(baseId);
    }

    @Override
    public Base getStudentOfBaseByBaseIdAndPage(Map map) {
        return classStudentMapper.getStudentOfBaseByBaseIdAndPage(map);
    }
}
