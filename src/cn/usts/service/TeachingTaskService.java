package cn.usts.service;

import cn.usts.pojo.TeachingTask;

import java.util.List;

public interface TeachingTaskService {

    void save(TeachingTask teachingTask);

    List<TeachingTask> queryAll(TeachingTask teachingTask);

    int getCount(TeachingTask teachingTask);

    void updateFile(TeachingTask teachingTask);

    void update(TeachingTask teachingTask);

    void delete(TeachingTask teachingTask);

    List<TeachingTask> queryById(Integer id);


}
