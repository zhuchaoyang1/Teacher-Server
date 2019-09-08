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
     * @param sysUser
     * @return
     */
    public List<SysUser> queryByBean(SysUser sysUser) {
        return userDao.queryByBean(sysUser);
    }

}
