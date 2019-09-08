package cn.usts.service;

import cn.usts.pojo.SysUser;

import java.util.List;

/**
 * 用户Service
 * @Author: ${朱朝阳}
 * @Date: 2019/7/14 23:04
 */

public interface UserService {

    /**
     * 登录
     * 查询SysUser表
     * @param sysUser
     * @return
     */
    List<SysUser> queryByBean(SysUser sysUser);

}
