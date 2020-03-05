package cn.usts.service;

import cn.usts.pojo.TeachingActivities;

import java.util.List;

public interface TeachingActivitiesService {

    int save(TeachingActivities teachingActivities);

    List<TeachingActivities> queryByBean(TeachingActivities teachingActivities);

    int queryCount(TeachingActivities teachingActivities);

    void delete(TeachingActivities teachingActivities);

    List<TeachingActivities> queryById(Integer id);

    void updateFileInfo(TeachingActivities teachingActivities);

    void update(TeachingActivities teachingActivities);
}
