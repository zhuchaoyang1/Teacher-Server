package cn.usts.service;

import cn.usts.pojo.Leavel;

import java.util.List;
import java.util.Map;

public interface LeavelService {

    List<Map<String, Object>> queryAll();

    void save(Leavel leavel);

    void delete(Leavel leavel);

    List<Leavel> queryById(Integer id);

}
