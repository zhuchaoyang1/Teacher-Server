package cn.usts.service.impl;

import cn.usts.dao.TeacherTempleteDao;
import cn.usts.pojo.TeacherTemplete;
import cn.usts.service.TeacherTempleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherTempleteServiceImpl implements TeacherTempleteService {

    @Autowired
    private TeacherTempleteDao teacherTempleteDao;

    @Override
    public int save(TeacherTemplete teacherTemplete) {
        return teacherTempleteDao.save(teacherTemplete);
    }

    @Override
    public List<TeacherTemplete> queryAll(TeacherTemplete teacherTemplete) {
        return teacherTempleteDao.queryAllS(teacherTemplete);
    }

    @Override
    public void delete(TeacherTemplete teacherTemplete) {
        teacherTempleteDao.delete(teacherTemplete);
    }

    @Override
    public List<TeacherTemplete> queryByBean(TeacherTemplete teacherTemplete) {
        return teacherTempleteDao.queryByBean(teacherTemplete);
    }

    @Override
    public int queryCount(TeacherTemplete teacherTemplete) {
        return teacherTempleteDao.queryCount(teacherTemplete);
    }
}
