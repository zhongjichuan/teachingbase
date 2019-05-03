package com.teachingbase.service.impl;

import com.teachingbase.dao.BaseMapper;
import com.teachingbase.domain.Base;
import com.teachingbase.domain.User;
import com.teachingbase.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
