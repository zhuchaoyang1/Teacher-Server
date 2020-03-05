package cn.usts.service.impl;

import cn.usts.dao.FormDao;
import cn.usts.pojo.FormData;
import cn.usts.pojo.page.PageOrSize;
import cn.usts.service.FormService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/17 0:17
 */
@Service
@Transactional
public class FormServiceImpl implements FormService {

    @Resource
    private FormDao formDao;

    /**
     * 保存数据
     *
     * @param formData Bean
     * @return
     */
    public int save(FormData formData) {
        return formDao.save(formData);
    }

    /**
     * 查询长度
     *
     * @param pageOrSize
     * @return
     */
    public int getCountSize(PageOrSize pageOrSize) {
        return formDao.getCountSize(pageOrSize);
    }

    /**
     * 分页 AND 根据ID查询表单
     *
     * @param pageOrSize
     * @return
     */
    public List<FormData> pageFormDataByID(PageOrSize pageOrSize) {
        return formDao.queryByIdPage(pageOrSize);
    }

    /**
     * 多条件查询数量
     *
     * @param pageOrSize Bean
     * @return
     */
    public int formDataByConCount(PageOrSize pageOrSize) {
        return formDao.formDataByConCount(pageOrSize);
    }

    /**
     * 筛选性查询表单
     *
     * @param pageOrSize Bean
     * @return
     */
    public List<FormData> formDataByConditions(PageOrSize pageOrSize) {
        return formDao.formDataByConditions(pageOrSize);
    }

    /**
     * 根据条件去查询所有数据
     * 不带有分页
     * 用于校领导或老师筛选下载
     *
     * @return list
     */
    public List<FormData> formDataByConNoPage(PageOrSize pageOrSize) {
        return formDao.formDataByConNoPage(pageOrSize);
    }

    @Override
    public List<FormData> queryByBean(FormData formData) {
        return formDao.queryByBean(formData);
    }

    @Override
    public boolean deleteRelation(Map<String, String> map) {
        // 获取表格名称
        String tName = map.get("tName");
        int pId = Integer.parseInt(map.get("pId"));
        FormData formData = new FormData();
        formData.setTableName(tName);
        formData.setpId(pId);
        try {
            formDao.updateFileNameAndPath(formData);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void update(FormData formData) {
        formDao.update(formData);
    }

    @Override
    public List<FormData> queryBeBean2(FormData formData) {
        return formDao.queryBeBean2(formData);
    }

    @Override
    public void delete(FormData formData) {
        formDao.delete(formData);
    }
}
