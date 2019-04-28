package com.teachingbase.domain;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {

    private String id; //节点ID

    private String parentId; //父节点ID

    private Boolean hasChildren; //是否有子节点

    private Boolean hasParent; //是否有父节点

    private String name; //节点名称

    private String url; //节点URL

    private String iconUrl; //图标

    private List<Tree<T>> children = new ArrayList<>(); //子节点信息

    @Override
    public String toString() {
        return "Tree{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", hasChildren=" + hasChildren +
                ", hasParent=" + hasParent +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", children=" + children +
                '}';
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Boolean getHasParent() {
        return hasParent;
    }

    public void setHasParent(Boolean hasParent) {
        this.hasParent = hasParent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }
}
