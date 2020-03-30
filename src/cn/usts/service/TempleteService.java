package cn.usts.service;

import cn.usts.pojo.Templete;

import java.util.List;

public interface TempleteService {

    List<Templete> queryAll();

    void update(Templete templete);

    List<Templete> queryByBean(Templete templete);

}
