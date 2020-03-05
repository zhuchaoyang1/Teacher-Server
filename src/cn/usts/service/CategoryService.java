package cn.usts.service;

import cn.usts.pojo.CategoryEntity;
import cn.usts.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryService {

    void addCategory(CategoryVO categoryVO);

    List<CategoryEntity> queryAll();

    void delete(CategoryEntity categoryEntity);
}
