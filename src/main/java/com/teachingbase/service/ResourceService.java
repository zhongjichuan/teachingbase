package com.teachingbase.service;

import com.teachingbase.domain.Resource;
import com.teachingbase.domain.Tree;

import java.util.List;

public interface ResourceService {

    public List<Tree<Resource>> getResourceByUsername(String username);
}
