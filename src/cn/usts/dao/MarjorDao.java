package cn.usts.dao;

import cn.usts.dao.base.IBaseDao;
import cn.usts.pojo.Marjor;

import java.util.List;

public interface MarjorDao extends IBaseDao<Marjor> {

    List<Marjor> findAllByCollegeName(Marjor marjor);

    List<String> findAllmarjorNamesByCollege(Marjor marjor);

}
