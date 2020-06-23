package cn.usts.dao;

import cn.usts.dao.base.IBaseDao;
import cn.usts.pojo.CategoryEntity;
import cn.usts.pojo.Templete;

public interface TempleteDao extends IBaseDao<Templete> {

    /**
     * 教务自定义的模板文件名称
     * @param templete
     */
    void updateMoudleFileName(Templete templete);

    Templete queryById(Templete templete);

}
