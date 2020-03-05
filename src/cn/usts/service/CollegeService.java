package cn.usts.service;

import cn.usts.pojo.College;

import java.util.List;

public interface CollegeService {

    List<College> queryByBean(College college);

    int addCollege(College college);

    List<College> queryAll();

    void delete(College college);

    List<College> queryById(int id);

    List<String> queryAllCollegeNames();
}
