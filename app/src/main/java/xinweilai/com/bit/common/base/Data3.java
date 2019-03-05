package xinweilai.com.bit.common.base;

/**
 * Created by 空 on 2017/11/29 0029.
 */
public class Data3 {
    /**
     * roleId : 2
     * systemType : 0
     * systemRole : 1
     * roleCode : main_leader
     * roleName : 主要领导
     * remark : 县级、区级领导
     */

//    roleCode 角色权限
//    sysadmin 系统管理员
//    main_leader 主要领导 县级、区级领导
//    ndrc_admin 发改局管理员
//    resp_admin 责任单位管理员
//    pm_leader 分管领导

    private int roleId;
    private int systemType;
    private int systemRole;
    private String roleCode;
    private String roleName;
    private String remark;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getSystemType() {
        return systemType;
    }

    public void setSystemType(int systemType) {
        this.systemType = systemType;
    }

    public int getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(int systemRole) {
        this.systemRole = systemRole;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
