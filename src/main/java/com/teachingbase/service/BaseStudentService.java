package com.teachingbase.service;

import com.teachingbase.dao.BaseStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public interface BaseStudentService {

    public boolean delBaseStudentByStudentId(String studentId,String baseId);

    public boolean insertBaseStudent(String baseId,String username);

    public int getCountStudentOfBase(String baseId);

    public boolean updateBaseReportStatus(Map map);
}
