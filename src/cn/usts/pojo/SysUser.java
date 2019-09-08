package cn.usts.pojo;

/**
 * 用户相关Bean
 * @Author: 朱朝阳
 * @Date: 2019/7/14 22:34
 */

public class SysUser {

    /** 唯一标识 */
    private Integer id;
    /** 登录账号 */
    private String name;
    /** 登录密码 */
    private String password;
    /**
     * 当前角色
     * 0：系统管理员
     * 1：学院领导
     * 2：老师
     */
    private Integer role;

    /**
     * 教师姓名
     */
    private String realName;

    /**
     * 教师所在专业名称
     */
    private String major;

    public SysUser(){}

    public SysUser(Integer id,String name,String password,Integer role,String realName,String major){
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.realName = realName;
        this.major = major;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRealName() {
        return realName;
    }

    public void setReadName(String realName) {
        this.realName = realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", realName='" + realName + '\'' +
                ", majar='" + major + '\'' +
                '}';
    }
}
