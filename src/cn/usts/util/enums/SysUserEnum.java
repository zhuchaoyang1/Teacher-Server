package cn.usts.util.enums;

/**
 * 角色标识符到中文的映射
 */
public enum SysUserEnum {

    GLY(0,"系统管理员"),
    YLD(1,"学院领导"),
    LS(2,"老师"),
    ZYZR(3,"系(专业)主任"),
    JWZR(4,"教务主任"),
    JXDD(5,"教学督导");

    private Integer role;

    private String roleStr;

    SysUserEnum(Integer role,String roleStr) {
        this.role = role;
        this.roleStr = roleStr;
    }

    public Integer getRole() {
        return role;
    }

    public String getRoleStr() {
        return roleStr;
    }
}
