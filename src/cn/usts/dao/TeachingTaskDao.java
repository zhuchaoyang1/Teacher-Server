package cn.usts.dao;

import cn.usts.dao.base.IBaseDao;
import cn.usts.pojo.TeachingTask;

import java.util.List;

public interface TeachingTaskDao extends IBaseDao<TeachingTask> {

    int getCount(TeachingTask teachingTask);

    void updateFile(TeachingTask teachingTask);

    List<TeachingTask> queryAll(TeachingTask teachingTask);

}
