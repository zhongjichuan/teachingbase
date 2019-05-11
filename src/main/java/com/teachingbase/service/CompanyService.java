package com.teachingbase.service;

import com.teachingbase.domain.Base;
import com.teachingbase.domain.Company;

public interface CompanyService {
    public Company getCompanyByBaseId(Base base);
}
