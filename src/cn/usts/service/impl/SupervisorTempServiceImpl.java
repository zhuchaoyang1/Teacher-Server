package cn.usts.service.impl;

import cn.usts.dao.SupervisorTempDao;
import cn.usts.pojo.SupervisorTemp;
import cn.usts.service.SupervisorTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SupervisorTempServiceImpl implements SupervisorTempService {

    @Autowired
    private SupervisorTempDao supervisorTempDao;

    @Override
    public int save(SupervisorTemp supervisorTemp) {
        return supervisorTempDao.save(supervisorTemp);
    }

    @Override
    public void delete(SupervisorTemp supervisorTemp) {
        supervisorTempDao.delete(supervisorTemp);
    }
}
