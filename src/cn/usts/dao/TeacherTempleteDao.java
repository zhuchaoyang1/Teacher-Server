package cn.usts.dao;

import cn.usts.dao.base.IBaseDao;
import cn.usts.pojo.TeacherTemplete;

import java.util.List;

public interface TeacherTempleteDao extends IBaseDao<TeacherTemplete> {

    List<TeacherTemplete> queryAllS(TeacherTemplete teacherTemplete);

    int queryCount(TeacherTemplete teacherTemplete);
}
