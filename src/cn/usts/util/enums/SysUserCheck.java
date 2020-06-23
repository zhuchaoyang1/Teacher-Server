package cn.usts.util.enums;

/**
 * 检查用户批量注册或者单个注册
 * 信息体检查
 */
public enum SysUserCheck {

    NO_ROLE_STR(0, "角色不能为空"),
    NO_NAME(1, "账号不能为空"),
    NO_PASS(2, "密码不能为空"),
    NO_REAL_NAME(3, "真实姓名不能为空"),
    NO_MARJOR(4, "专业不能为空"),
    NO_PHONE(5, "电话不能为空"),
    NO_COLLEGE(6, "学院不能为空");

    private final Integer checkType;

    private final String str;

    SysUserCheck(Integer checkType, String str) {
        this.checkType = checkType;
        this.str = str;
    }

    public Integer getCheckType() {
        return checkType;
    }

    public String getStr() {
        return str;
    }
}
