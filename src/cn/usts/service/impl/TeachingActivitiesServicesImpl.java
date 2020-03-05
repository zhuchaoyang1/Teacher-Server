package cn.usts.service.impl;

import cn.usts.dao.TeachingActivitiesDao;
import cn.usts.pojo.TeachingActivities;
import cn.usts.service.TeachingActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeachingActivitiesServicesImpl implements TeachingActivitiesService {

    @Autowired
    private TeachingActivitiesDao teachingActivitiesDao;

    @Override
    public int save(TeachingActivities teachingActivities) {
        return teachingActivitiesDao.save(teachingActivities);
    }

    @Override
    public List<TeachingActivities> queryByBean(TeachingActivities teachingActivities) {
        return teachingActivitiesDao.queryByBean(teachingActivities);
    }

    @Override
    public int queryCount(TeachingActivities teachingActivities) {
        return teachingActivitiesDao.queryCount(teachingActivities);
    }

    @Override
    public void delete(TeachingActivities teachingActivities) {
        teachingActivitiesDao.delete(teachingActivities);
    }

    @Override
    public List<TeachingActivities> queryById(Integer id) {
        return teachingActivitiesDao.queryById(id);
    }

    @Override
    public void updateFileInfo(TeachingActivities teachingActivities) {
        teachingActivitiesDao.updateFileInfo(teachingActivities);
    }

    @Override
    public void update(TeachingActivities teachingActivities) {
        teachingActivitiesDao.update(teachingActivities);
    }
}
