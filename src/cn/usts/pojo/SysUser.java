package cn.usts.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户相关Bean
 *
 * @Author: 朱朝阳
 * @Date: 2019/7/14 22:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {

    /**
     * 唯一标识
     */
    private Integer id;
    /**
     * 登录账号
     */
    private String name;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 当前角色
     * 0：系统管理员
     * 1：学院领导
     * 2：老师
     * 3：系(专业)主任: 可以看到当前专业下的所有老师资料
     * 4：教务主任
     * 5：教学督导
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

    /**
     * 教师联系方式
     */
    private String phone;

    /**
     * 教师所在学院
     */
    private String college;

    private String userToken;

    private String roleStr;

    public SysUser(String realName, String name, String college) {
        this.realName = realName;
        this.name = name;
        this.college = college;
    }

    public SysUser(Integer id) {
        this.id = id;
    }
}
