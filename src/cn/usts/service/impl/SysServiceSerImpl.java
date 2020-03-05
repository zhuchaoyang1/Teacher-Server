package cn.usts.service.impl;

import cn.usts.dao.SysServiceDao;
import cn.usts.pojo.SysService;
import cn.usts.pojo.SysUser;
import cn.usts.service.SysServiceSer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/8/1 23:32
 */
@Service
public class SysServiceSerImpl implements SysServiceSer {

    @Resource
    private SysServiceDao sysServiceDao;

    public void save(SysService sysService) {
        sysServiceDao.save(sysService);
    }

    @Override
    public List<SysService> queryByBean(SysService sysService) {
        return sysServiceDao.queryByBean(sysService);
    }

    @Override
    public int getCountSize(SysService sysService) {
        return sysServiceDao.getCountSize(sysService);
    }


}
