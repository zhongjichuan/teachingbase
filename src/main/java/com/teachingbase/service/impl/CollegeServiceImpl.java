package com.teachingbase.service.impl;

import com.teachingbase.dao.CollegeMapper;
import com.teachingbase.domain.College;
import com.teachingbase.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    public CollegeMapper collegeMapper;
    @Override
    public List<College> getCollegeList() {
        return collegeMapper.getCollegeList();
    }
}
