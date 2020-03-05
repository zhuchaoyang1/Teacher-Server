package cn.usts.dao;

import cn.usts.dao.base.IBaseDao;
import cn.usts.pojo.Supervisor;
import cn.usts.pojo.TeachingTask;

import java.util.List;

public interface SupervisorDao extends IBaseDao<Supervisor> {

    int getCount(Supervisor supervisor);

    void updateFile(Supervisor supervisor);

    List<Supervisor> queryAll(Supervisor supervisor);

    List<Supervisor> queryByBean(Supervisor supervisor);

    List<Supervisor> queryByBean2(Supervisor supervisor);

}
