package cn.usts.service;

import cn.usts.pojo.FormData;
import cn.usts.pojo.page.PageOrSize;

import java.util.List;
import java.util.Map;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/17 0:13
 */

public interface FormService {

    /**
     * 保存表单
     *
     * @param formData Bean
     * @return
     */
    int save(FormData formData);

    /**
     * 查询Form按照条件查询长度
     *
     * @param pageOrSize
     * @return
     */
    int getCountSize(PageOrSize pageOrSize);

    /**
     * 分页 AND 根据ID 查询表单
     *
     * @param pageOrSize
     * @return
     */
    List<FormData> pageFormDataByID(PageOrSize pageOrSize);


    /**
     * 多条件查询数量
     *
     * @param pageOrSize Bean
     * @return
     */
    int formDataByConCount(PageOrSize pageOrSize);

    /**
     * 筛选性查询表单
     *
     * @param pageOrSize
     * @return
     */
    List<FormData> formDataByConditions(PageOrSize pageOrSize);

    /**
     * 根据条件去查询所有数据
     * 不带有分页
     * 用于校领导或老师筛选下载
     *
     * @return list
     */
    List<FormData> formDataByConNoPage(PageOrSize pageOrSize);

    List<FormData> queryByBean(FormData formData);

    /**
     * 根据表格名称 在删除文件之后，同时更新当前表格的文件路径以及文件名称为空
     *
     * @param map
     * @return
     */
    boolean deleteRelation(Map<String, String> map);

    void update(FormData formData);

    /**
     * 查看是否有权限 TODO 可以优化上面的quertByBean 由于时间关系暂未优化
     *
     * @param formData
     * @return
     */
    List<FormData> queryBeBean2(FormData formData);

    void delete(FormData formData);

}
