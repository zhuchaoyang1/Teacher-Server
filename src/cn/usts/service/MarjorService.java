package cn.usts.service;

import cn.usts.pojo.Marjor;

import java.util.List;
import java.util.Map;

public interface MarjorService {

    int saveMarjor(Marjor marjor);

    List<Marjor> queryAll();

    void delete(Marjor marjor);

    String batchImport(Map<String, List<Marjor>> map, String socketId);

    List<Marjor> findAllByCollegeName(Marjor marjor);

    List<String> findAllmarjorNamesByCollege(Marjor marjor);
}
