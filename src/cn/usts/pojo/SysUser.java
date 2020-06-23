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


    // 新增教师教资字段
    private Integer age;
    private Boolean gender;         // true:男  false:女
    private String nation;          // 民族
    private String titles;          // 职称
    private String education;       // 学历
    private String diplomaPath;     // 学历证书
    private String photoPath;       // 照片
    private String introduction;    // 简单介绍

    private String oldPersonPath;   // 用于用户更新个人照片。保存原数据用于删除照片
    private String oldDiplomaPath;  // 保存原数据用于删除照片
    private String oldPwd;          // 判断是否更新过密码
    private String isUpdatePwdFlag;    // 是否需要SQL更新密码

    private Integer years;          // 提交年龄的入库年份  用于每次计算年纪差值


    public SysUser(String realName, String name, String college) {
        this.realName = realName;
        this.name = name;
        this.college = college;
    }

    public SysUser(Integer id) {
        this.id = id;
    }
}
