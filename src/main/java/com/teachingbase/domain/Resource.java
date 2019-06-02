package com.teachingbase.domain;

public class Resource {
    private String resourceId;
    private String parentId;
    private String description;
    private String url;
    private String permissions;
    private String iconUrl;
    private String isActive;


    @Override
    public String toString() {
        return "Resource{" +
                "resourceId='" + resourceId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", permissions='" + permissions + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
