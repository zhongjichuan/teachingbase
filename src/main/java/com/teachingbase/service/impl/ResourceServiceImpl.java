package com.teachingbase.service.impl;

import com.teachingbase.dao.ResourceMapper;
import com.teachingbase.domain.Resource;
import com.teachingbase.domain.Tree;
import com.teachingbase.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Tree<Resource>> getResourceByUsername(String username) {
        List<Resource> resourceList = resourceMapper.getResourceByUsername(username);
        //将资源路径转换成tree
        List<Tree<Resource>> treeList = new ArrayList<>();
        for(Resource resource : resourceList){
            Tree<Resource> tree = new Tree<Resource>();
            tree.setId(resource.getResourceId());
            tree.setParentId(resource.getParentId());
            tree.setUrl(resource.getUrl());
            tree.setName(resource.getDescription());
            tree.setIconUrl(resource.getIconUrl());
            treeList.add(tree);
        }

        return buildTree(treeList);
    }

    /**
    创建菜单资源tree,对父节点子节点进行归属。
     */
    private List<Tree<Resource>> buildTree(List<Tree<Resource>> treeList){

        if (treeList == null) {
            return null;
        }

        List<Tree<Resource>> tree = new ArrayList<>();
        for(Tree children : treeList){
            String pid = children.getParentId();
            if (pid == null || pid.equals("0")) {
                //是父节点
                tree.add(children);
                continue;
            }
            //不是父节点，则遍历全部节点，找到为当前节点children
            for (Tree<Resource> node : treeList) {
                String id = node.getId();
                if (id != null && id.equals(pid)) {
                    //说明是该节点是children的父节点
                    children.setHasParent(true);
                    node.setHasChildren(true);
                    node.getChildren().add(children);
                    continue;
                }
            }
        };
        return tree;
    }
}
