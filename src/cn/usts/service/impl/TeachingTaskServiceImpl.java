package cn.usts.service.impl;

import cn.usts.dao.TeachingTaskDao;
import cn.usts.pojo.TeachingTask;
import cn.usts.service.TeachingTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeachingTaskServiceImpl implements TeachingTaskService {

    @Autowired
    private TeachingTaskDao teachingTaskDao;

    @Override
    public void save(TeachingTask teachingTask) {
        teachingTaskDao.save(teachingTask);
    }

    @Override
    public List<TeachingTask> queryAll(TeachingTask teachingTask) {
        return teachingTaskDao.queryAll(teachingTask);
    }

    @Override
    public int getCount(TeachingTask teachingTask) {
        return teachingTaskDao.getCount(teachingTask);
    }

    @Override
    public void updateFile(TeachingTask teachingTask) {
        teachingTaskDao.updateFile(teachingTask);
    }

    @Override
    public void update(TeachingTask teachingTask) {
        teachingTaskDao.update(teachingTask);
    }

    @Override
    public void delete(TeachingTask teachingTask) {
        teachingTaskDao.delete(teachingTask);
    }

    @Override
    public List<TeachingTask> queryById(Integer id) {
        return teachingTaskDao.queryById(id);
    }


}
