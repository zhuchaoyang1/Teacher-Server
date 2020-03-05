package cn.usts.dao;

import cn.usts.dao.base.IBaseDao;
import cn.usts.pojo.College;

import java.util.List;

/**
 * 学院相关
 */
public interface CollegeDao extends IBaseDao<College> {

    List<String> queryAllCollegeNames();

}
