package cn.usts.service.impl;

import cn.usts.dao.TempleteDao;
import cn.usts.pojo.Templete;
import cn.usts.service.TempleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TempleteServiceImpl implements TempleteService {

    @Autowired
    private TempleteDao templeteDao;

    @Override
    public List<Templete> queryAll() {
        return templeteDao.queryAll();
    }

    @Override
    public void update(Templete templete) {
        templeteDao.update(templete);
    }

    @Override
    public List<Templete> queryByBean(Templete templete) {
        return templeteDao.queryByBean(templete);
    }

}
