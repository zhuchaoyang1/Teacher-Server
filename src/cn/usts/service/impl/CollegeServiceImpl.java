package cn.usts.service.impl;

import cn.usts.dao.CollegeDao;
import cn.usts.pojo.College;
import cn.usts.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeDao collegeDao;

    @Override
    public List<College> queryByBean(College college) {
        return collegeDao.queryByBean(college);
    }

    @Override
    public int addCollege(College college) {
        return collegeDao.save(college);
    }

    @Override
    public List<College> queryAll() {
        return collegeDao.queryAll();
    }

    @Override
    public void delete(College college) {
        collegeDao.delete(college);
    }

    @Override
    public List<College> queryById(int id) {
        return collegeDao.queryById(id);
    }

    @Override
    public List<String> queryAllCollegeNames() {
        return collegeDao.queryAllCollegeNames();
    }
}
