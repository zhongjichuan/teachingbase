package com.teachingbase.service.impl;

import com.teachingbase.dao.CompanyMapper;
import com.teachingbase.domain.Base;
import com.teachingbase.domain.Company;
import com.teachingbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Override
    public Company getCompanyByBaseId(Base base) {
        return companyMapper.getCompanyByBaseId(base);
    }

    public Company getCompanyByCompanyName(String companyName){
        return companyMapper.getCompanyByCompanyName(companyName);
    }
}
