package com.teachingbase.service.impl;

import com.teachingbase.dao.BaseMapper;
import com.teachingbase.domain.Base;
import com.teachingbase.domain.Company;
import com.teachingbase.domain.User;
import com.teachingbase.service.BaseService;
import com.teachingbase.util.SessionContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    public BaseMapper baseMapper;
    public List<Base> getBaseListByCompanyName(String companyName){
        List<Base> list = baseMapper.getBaseListByCompanyName(companyName);
        return list;
    }

    public List getBaseListBySearchParams(Map params){
        return baseMapper.getBaseListBySearchParams(params);
    }

    @Override
    public List getBaseTeachersByCompanyName(String companyName) {
        return baseMapper.getBaseTeachersByCompanyName(companyName);
    }

    @Override
    public String getMaxBaseId() {
        return baseMapper.getMaxBaseId();
    }

    @Transactional
    public boolean addBaseByManager(Base base,String collegeId,String teacherId,String companyId){
        int result1 = baseMapper.addBaseByManager(base);//插入base表
        int result2 = baseMapper.saveBaseCompany(base.getBaseId(),companyId);
        int result3 = baseMapper.saveBaseCollege(base.getBaseId(),collegeId);
        int result4 = baseMapper.saveBaseTeacher(base.getBaseId(),teacherId);
        if (result1==1&&result2==1&&result3==1&&result4==1){
            return true;
        }else {
            System.out.println("手动进行事务回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动开启事务回滚
            return false;
        }
    }

    /**
     * 新建基地，会对多个表进行插入操作,辅导员
     * @param base
     * @param
     * @return
     */
    @Transactional
    @Override
    public boolean addBase(Base base,String companyId,String collegeId) {
        int result1 = baseMapper.addBase(base);//插入base表
        int result2 = baseMapper.saveBaseCompany(base.getBaseId(),companyId);
        int result3 = baseMapper.saveBaseCollege(base.getBaseId(),collegeId);
        int result4 = baseMapper.saveBaseTeacherByCounselor(base.getBaseId());
        if (result1==1&&result2==1&&result3==1&&result4==1){
            return true;
        }else {
            System.out.println("手动进行事务回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动开启事务回滚
            return false;
        }
    }

    @Override
    public int saveBaseTeacher(String baseId, String teacherId) {
        return baseMapper.saveBaseTeacher(baseId,teacherId);
    }

    @Override
    public int saveBaseCompany(String baseId, String companyId) {
        return baseMapper.saveBaseCompany(baseId,companyId);
    }

    @Override
    public Company getCompanyByCompanyName(String companyName) {
        return baseMapper.getCompanyByCompanyName(companyName);
    }

    @Override
    public Base getBaseByBaseId(String baseId) {
        return baseMapper.getBaseByBaseId(baseId);
    }

    @Transactional
    @Override
    public boolean updateBase(Base base, String teacherId) {
        int result1 = baseMapper.updateBase(base);
        int result2 = baseMapper.updateBaseTeacher(base.getBaseId(),teacherId);
        if (result1==1&&result2==1){
            return true;
        }else {
            System.out.println("手动进行事务回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动开启事务回滚
            return false;
        }
    }

    @Override
    public List<Base> getBaseListByCollegeName(String collegeName) {
        return baseMapper.getBaseListByCollegeName(collegeName);
    }

    @Override
    public List getBaseListBySearchParamsAndCollege(Map params) {
        return baseMapper.getBaseListBySearchParamsAndCollege(params);
    }

    @Override
    public boolean updateBaseByCounselor(Base base) {
       int i = baseMapper.updateBaseByCounselor(base);
       if (i == 1){
           return true;
       }else
           return false;
    }

    /**
     * 删除base,删除多个表
     * @param base
     * @return
     */
    @Transactional
    @Override
    public boolean delBaseByBaseId(Base base) {
        int result1 = baseMapper.delBaseByBaseId(base.getBaseId());
        int result2 = baseMapper.delCollegeBaseByBaseId(base.getBaseId());
        int result4 = baseMapper.delTeacherBaseByBaseId(base.getBaseId());//删除teacher_base表
        int result3 = baseMapper.delCompanyBaseByBaseId(base.getBaseId());

        if (result1==1&&result2==1&&result3==1&&result4==1){
            return true;
        }else {
            System.out.println("手动进行事务回滚");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动开启事务回滚
            return false;
        }


//        if (base.getBaseStatus().equals("0")){//是否分配了老师。0 未分配 1 分配
//            if (result1==1&&result2==1&&result3==1){
//                return true;
//            }else {
//                System.out.println("手动进行事务回滚");
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动开启事务回滚
//                return false;
//            }
//        }else{
//            int result4 = baseMapper.delTeacherBaseByBaseId(base.getBaseId());//删除teacher_base表
//            if (result1==1&&result2==1&&result3==1&&result4==1){
//                return true;
//            }else {
//                System.out.println("手动进行事务回滚");
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动开启事务回滚
//                return false;
//            }
//        }
    }


    /**
     * 学生相关操作
     * @param username
     * @return
     */
    @Override
    public List<Base> getBaseListByStudent(String username) {
        return baseMapper.getBaseListByStudent(username);
    }

    @Override
    public List<Base> getBaseListByStudentAndParams(Map map) {
        return baseMapper.getBaseListByStudentAndParams(map);
    }

    @Override
    public Base getBaseByStudentId(String studentId) {
        return baseMapper.getBaseByStudentId(studentId);
    }

    /**
     * 实训教师
     * @param teacherId
     * @return
     */
    @Override
    public List<Base> getBaseByTeacherId(String teacherId) {
        return baseMapper.getBaseByTeacherId(teacherId);
    }

}
