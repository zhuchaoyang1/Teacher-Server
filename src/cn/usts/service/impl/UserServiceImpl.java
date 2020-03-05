package cn.usts.service.impl;

import cn.usts.dao.UserDao;
import cn.usts.pojo.SysUser;
import cn.usts.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户ServiceImplement
 *
 * @Author: ${朱朝阳}
 * @Date: 2019/7/14 23:05
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 登录
     * 查询SysUser表
     *
     * @param sysUser
     * @return
     */
    public List<SysUser> queryByBean(SysUser sysUser) {
        return userDao.queryByBean(sysUser);
    }

    @Override
    public List<SysUser> queryAllStaffTeachers() {
        return userDao.queryAllTeachers();
    }

    @Override
    public int save(SysUser sysUser) {
        return userDao.save(sysUser);
    }

    @Override
    public List<SysUser> queryByRealNameAndUsernameAndCollege(SysUser sysUser) {
        return userDao.queryByRealNameAndUsernameAndCollege(sysUser);
    }

    @Override
    public List<SysUser> queryByRealNameAndSoOn(SysUser sysUser) {
        return userDao.queryByRealNameAndSoOn(sysUser);
    }

    @Override
    public void updatePwd(SysUser sysUser) {
        userDao.updatePwd(sysUser);
    }

    @Override
    public List<SysUser> queryById(int id) {
        return userDao.queryById(id);
    }

    @Override
    public List<SysUser> queryAllCollegeOrMarjor(SysUser sysUser) {
        return userDao.queryAllCollegeOrMarjor(sysUser);
    }

    @Override
    public List<SysUser> queryByCollege(SysUser sysUser) {
        return userDao.queryByCollege(sysUser);
    }

    @Override
    public SysUser queryRealNameById(SysUser sysUser) {
        return userDao.queryRealNameById(sysUser);
    }

}
