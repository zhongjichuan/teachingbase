package com.teachingbase.service.impl;

import com.teachingbase.dao.RoleMapper;
import com.teachingbase.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public String getRoleCodeByUsername(String username) {
        return roleMapper.getRoleCodeByUsername(username);
    }
}
