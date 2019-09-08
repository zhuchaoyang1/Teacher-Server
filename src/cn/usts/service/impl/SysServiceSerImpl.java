package cn.usts.service.impl;

import cn.usts.dao.SysServiceDao;
import cn.usts.pojo.SysService;
import cn.usts.service.SysServiceSer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/8/1 23:32
 */
@Service
public class SysServiceSerImpl implements SysServiceSer {

    @Resource
    private SysServiceDao sysServiceDao;

    public void save(SysService sysService){
        sysServiceDao.save(sysService);
    }

}
