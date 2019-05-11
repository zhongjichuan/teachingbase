package com.teachingbase.service.impl;

import com.teachingbase.dao.CompanyMapper;
import com.teachingbase.dao.TeacherMapper;
import com.teachingbase.domain.Company;
import com.teachingbase.domain.Teacher;
import com.teachingbase.domain.User;
import com.teachingbase.service.TeacherService;
import com.teachingbase.util.SessionContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService  {

    @Autowired
    public TeacherMapper teacherMapper;

    @Autowired
    public CompanyMapper companyMapper;
    @Override
    public List getTeacherListByCompany(String companyName) {
        List list = teacherMapper.getTeacherListByCompany(companyName);
        return list;
    }

    @Override
    public List<Teacher> getTeacherListByParams(Map map) {
        return teacherMapper.getTeacherListByParams(map);
    }

    @Override
    public int getMaxTeacherIdByCompanyName(String companyName) {
        return teacherMapper.getMaxTeacherIdByCompanyName(companyName);
    }

    /**
     * 添加实训教师，更新两张表 teacher,company_teacher,user
     * @param teacher
     * @return
     */
    @Transactional
    @Override
    public boolean addTeacher(Teacher teacher) {
        String companyName = SessionContextUtil.getCurrentUser().getCompany();
        Company company = companyMapper.getCompanyByCompanyName(companyName);
        int result1 = teacherMapper.addTeacher(teacher);
        int result2 = teacherMapper.addCompanyTeacher(company.getCompanyId(),teacher.getTeacherId());
        User user = new User();
        user.setUsername(teacher.getTeacherId());
        user.setName(teacher.getTeacherName());
        int result3 = teacherMapper.addTeacherOfUserTable(user);
        if(result1==1&&result2==1&&result3==1){
            return true;
        }else{
            System.out.println("手动进行事务回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动开启事务回滚
            return false;
        }

    }

    @Transactional
    @Override
    public boolean updateTeacher(Teacher teacher) {
        int result1 = teacherMapper.updateTeacher(teacher);
        int result2 = teacherMapper.updateTeacherOfUserTable(teacher.getTeacherName(),teacher.getTeacherId());
        if(result1==1&&result2==1){
            return true;
        }else{
            System.out.println("手动进行事务回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动开启事务回滚
            return false;
        }
    }

    /**
     * 删除teacher
     * @param teacherId
     * @return
     */
    @Transactional
    @Override
    public boolean delTeacher(String teacherId) {
        int result1 = teacherMapper.delTeacher(teacherId);
        int result2 = teacherMapper.delTeacherOfUserTaber(teacherId);
        int result3 = teacherMapper.delCompanyTeacher(teacherId);
        int i = teacherMapper.countBaseTeacherByTeacherId(teacherId);
        boolean result4 = false;
        if (i>0){
            int n = teacherMapper.delBaseTeacher(teacherId);
            if (n==i){
                result4 = true;
            }
        }else {
            result4 = true;
        }
        if(result1==1&&result2==1&&result3==1&&result4){
            return true;
        }else{
            System.out.println("手动进行事务回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动开启事务回滚
            return false;
        }
    }
}
