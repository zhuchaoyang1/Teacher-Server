package cn.usts.service;

import cn.usts.pojo.TeacherTemplete;

import java.util.List;

public interface TeacherTempleteService {

    int save(TeacherTemplete teacherTemplete);

    List<TeacherTemplete> queryAll(TeacherTemplete teacherTemplete);

    void delete(TeacherTemplete teacherTemplete);

    List<TeacherTemplete> queryByBean(TeacherTemplete teacherTemplete);

    int queryCount(TeacherTemplete teacherTemplete);
}
