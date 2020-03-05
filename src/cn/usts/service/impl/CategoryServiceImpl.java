package cn.usts.service.impl;

import cn.usts.dao.CategoryDao;
import cn.usts.pojo.CategoryEntity;
import cn.usts.pojo.vo.CategoryVO;
import cn.usts.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public void addCategory(CategoryVO categoryVO) {
        // 获取categoryEntity数据分割
        List<String> cateNames = categoryVO.getCateNames();
        cateNames.forEach(var -> {
            CategoryEntity categoryEntity = CategoryEntity.builder()
                    .cateName(var).build();
            categoryDao.save(categoryEntity);
        });
    }

    @Override
    public List<CategoryEntity> queryAll() {
        return categoryDao.queryAll();
    }

    @Override
    public void delete(CategoryEntity categoryEntity) {
        categoryDao.delete(categoryEntity);
    }
}
