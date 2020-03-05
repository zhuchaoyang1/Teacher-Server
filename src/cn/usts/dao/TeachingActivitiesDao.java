package cn.usts.dao;

import cn.usts.dao.base.IBaseDao;
import cn.usts.pojo.TeachingActivities;

public interface TeachingActivitiesDao extends IBaseDao<TeachingActivities> {

    int queryCount(TeachingActivities teachingActivities);

    void updateFileInfo(TeachingActivities teachingActivities);

}
