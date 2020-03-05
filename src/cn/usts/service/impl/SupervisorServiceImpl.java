package cn.usts.service.impl;

import cn.usts.dao.SupervisorDao;
import cn.usts.pojo.Supervisor;
import cn.usts.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
    private SupervisorDao supervisorDao;

    @Override
    public void save(Supervisor supervisor) {
        supervisorDao.save(supervisor);
    }

    @Override
    public List<Supervisor> queryAll(Supervisor supervisor) {
        return supervisorDao.queryAll(supervisor);
    }

    @Override
    public int getCount(Supervisor supervisor) {
        return supervisorDao.getCount(supervisor);
    }

    @Override
    public void updateFile(Supervisor supervisor) {
        supervisorDao.updateFile(supervisor);
    }

    @Override
    public void update(Supervisor supervisor) {
        supervisorDao.update(supervisor);
    }

    @Override
    public void delete(Supervisor supervisor) {
        supervisorDao.delete(supervisor);
    }

    @Override
    public List<Supervisor> queryById(Integer id) {
        return supervisorDao.queryById(id);
    }

    @Override
    public List<Supervisor> queryByBean(Supervisor supervisor) {
        return supervisorDao.queryByBean(supervisor);
    }

    @Override
    public List<Supervisor> queryByBean2(Supervisor supervisor) {
        return supervisorDao.queryByBean2(supervisor);
    }


}
