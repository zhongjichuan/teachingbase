package com.teachingbase.domain;

public class Role {
    private String roleId;
    private String roleName;
    private String resourceId;
    private String roleCode;

    @Override
    public String toString() {
        return "Role{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", resourceId='" + resourceId + '\'' +
                ", roleCode='" + roleCode + '\'' +
                '}';
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getroleCode() {
        return roleCode;
    }

    public void setroleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
