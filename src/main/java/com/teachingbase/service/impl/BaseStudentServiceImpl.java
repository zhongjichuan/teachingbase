package com.teachingbase.service.impl;

import com.teachingbase.dao.BaseMapper;
import com.teachingbase.dao.BaseStudentMapper;
import com.teachingbase.service.BaseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class BaseStudentServiceImpl implements BaseStudentService {
    @Autowired
    public BaseStudentMapper baseStudentMapper;
    @Autowired
    public BaseMapper baseMapper;

    @Transactional
    @Override
    public boolean delBaseStudentByStudentId(String studentId,String baseId) {
        int i = baseStudentMapper.delBaseStudentByStudentId(studentId);
        int result = baseMapper.updateBaseEnrollNumByDecrease(baseId);
        return i>0&&result>0?true:false;
    }

    @Transactional
    @Override
    public boolean insertBaseStudent(String baseId, String username) {
        int i = baseStudentMapper.insertBaseStudent(baseId,username);
        int result = baseMapper.updateBaseEnrollNumByIncrease(baseId);
        return i>0&&result>0?true:false;
    }

    @Override
    public int getCountStudentOfBase(String baseId) {
        return baseStudentMapper.getCountStudentOfBase(baseId);
    }

    @Override
    public boolean updateBaseReportStatus(Map map) {
        return baseStudentMapper.updateBaseReportStatus(map)==1?true:false;
    }


}
