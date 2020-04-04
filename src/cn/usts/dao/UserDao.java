package cn.usts.dao;

import cn.usts.dao.base.IBaseDao;
import cn.usts.pojo.FormData;
import cn.usts.pojo.SysUser;

import java.util.List;

/**
 * UserDao
 *
 * @Author: 朱朝阳
 * @Date: 2019/7/14 22:48
 */

public interface UserDao extends IBaseDao<SysUser> {
    /**
     * 查询所有教师 用于指令发布
     *
     * @return
     */
    List<SysUser> queryAllTeachers();

    /**
     * 用于批量添加之前查询数据库中是否已经存在该条记录
     * {RealName:真实姓名}  {Username:账号}  {College:学院}
     *
     * @return
     */
    List<SysUser> queryByRealNameAndUsernameAndCollege(SysUser sysUser);

    /**
     * 重置密码的时候
     * 验证  真实姓名、手机号、账号
     *
     * @return
     */
    List<SysUser> queryByRealNameAndSoOn(SysUser sysUser);

    /**
     * 根据ID 更新密码
     *
     * @param sysUser
     */
    void updatePwd(SysUser sysUser);

    // 为系主任查询所有相关人员准备
    List<SysUser> queryAllCollegeOrMarjor(SysUser sysUser);

    List<SysUser> queryByCollege(SysUser sysUser);

    SysUser queryRealNameById(SysUser sysUser);

    List<SysUser> queryByFormId(FormData formData);

}
