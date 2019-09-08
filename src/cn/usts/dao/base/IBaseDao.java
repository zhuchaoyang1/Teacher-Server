package cn.usts.dao.base;

import cn.usts.pojo.page.PageOrSize;

import java.util.List;

/**
 * 基础Dao
 * 增删改查
 * @Author: 朱朝阳
 * @Date: 2019/7/14 22:45
 */

public interface IBaseDao<T> {

    /**
     * 保存
     * @param t Bean
     * @return  ID
     */
    int save(T t);

    /**
     * 删除
     * @param t Bean
     */
    void delete(T t);

    /**
     * 修改
     * @param t Bean
     */
    void update(T t);

    /**
     * 查询全部
     * @return
     */
    List<T> queryAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    List<T> queryById(int id);

    /**
     * 根据JavaBean查询
     * @param t Bean
     * @return
     */
    List<T> queryByBean(T t);

    /**
     * 查询所有JavaBean By page
     * @param pageOrSize 分页Bean
     * @return
     */
    List<T> queryByIdPage(PageOrSize pageOrSize);

    /**
     * 查询数据长度
     * @param t  PageOrSize
     * @return  Size
     */
    int getCountSize(T t);

}
