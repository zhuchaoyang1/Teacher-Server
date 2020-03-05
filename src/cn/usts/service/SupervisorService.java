package cn.usts.service;

import cn.usts.pojo.Supervisor;
import cn.usts.pojo.TeachingTask;

import java.util.List;

public interface SupervisorService {

    void save(Supervisor suoervisor);

    List<Supervisor> queryAll(Supervisor supervisor);

    int getCount(Supervisor supervisor);

    void updateFile(Supervisor supervisor);

    void update(Supervisor supervisor);

    void delete(Supervisor supervisor);

    List<Supervisor> queryById(Integer id);

    List<Supervisor> queryByBean(Supervisor supervisor);

    List<Supervisor> queryByBean2(Supervisor supervisor);

}
