package cn.usts.service;

import cn.usts.pojo.FormData;
import cn.usts.pojo.SysUser;

import java.util.List;

/**
 * 用户Service
 *
 * @Author: ${朱朝阳}
 * @Date: 2019/7/14 23:04
 */

public interface UserService {

    /**
     * 登录
     * 查询SysUser表
     *
     * @param sysUser
     * @return
     */
    List<SysUser> queryByBean(SysUser sysUser);

    List<SysUser> queryAllStaffTeachers();

    int save(SysUser sysUser);

    List<SysUser> queryByRealNameAndUsernameAndCollege(SysUser sysUser);

    List<SysUser> queryByRealNameAndSoOn(SysUser sysUser);

    void updatePwd(SysUser sysUser);

    List<SysUser> queryById(int id);

    List<SysUser> queryAllCollegeOrMarjor(SysUser sysUser);

    List<SysUser> queryByCollege(SysUser sysUser);

    SysUser queryRealNameById(SysUser sysUser);

    void update(SysUser sysUser);

    List<SysUser> queryByFormId(FormData formData);

}
