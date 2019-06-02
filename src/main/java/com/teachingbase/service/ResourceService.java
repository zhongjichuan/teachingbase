package com.teachingbase.service;

import com.teachingbase.domain.Resource;
import com.teachingbase.domain.Tree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceService {

    public List<Tree<Resource>> getResourceByUsername(String username);

    public List<Tree<Resource>> getAllResource();

    public boolean updateNode(String id,String name);

    public boolean updateResourceToActive(String id,String activeFlag);
}
