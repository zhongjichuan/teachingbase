package com.teachingbase.service;

import com.teachingbase.domain.Base;
import com.teachingbase.domain.User;

import java.util.List;
import java.util.Map;

public interface BaseService {

    public List<Base> getBaseListByCompanyName(String companyName);

    public List getBaseListBySearchParams(Map params);
}
