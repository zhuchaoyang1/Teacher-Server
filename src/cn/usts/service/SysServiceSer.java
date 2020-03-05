package cn.usts.service;

import cn.usts.pojo.SysService;
import cn.usts.pojo.SysUser;

import java.util.List;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/8/1 23:31
 */

public interface SysServiceSer {

    /**
     * 保存评价
     *
     * @param sysService
     */
    void save(SysService sysService);

    List<SysService> queryByBean(SysService sysService);

    int getCountSize(SysService sysService);

}
