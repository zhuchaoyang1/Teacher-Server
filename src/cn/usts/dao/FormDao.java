package cn.usts.dao;

import cn.usts.dao.base.IBaseDao;
import cn.usts.pojo.FormData;
import cn.usts.pojo.page.PageOrSize;

import java.util.List;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/16 23:18
 */

public interface FormDao extends IBaseDao<FormData>{

    /**
     * 查询数据长度
     * @param pageOrSize
     * @return  Size
     */
    int getCountSize(PageOrSize pageOrSize);

    /**
     * 多条件查询数量
     * @param pageOrSize    Bean
     * @return
     */
    int formDataByConCount(PageOrSize pageOrSize);

    /**
     * 根据条件去查询所有数据
     * 带有分页
     * 用于校领导或老师筛选并分页查看表格
     * @return  list
     */
    List<FormData> formDataByConditions(PageOrSize pageOrSize);


    /**
     * 根据条件去查询所有数据
     * 不带有分页
     * 用于校领导或老师筛选下载
     * @return  list
     */
    List<FormData> formDataByConNoPage(PageOrSize pageOrSize);




}
