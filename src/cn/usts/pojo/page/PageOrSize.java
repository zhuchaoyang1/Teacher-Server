package cn.usts.pojo.page;

import cn.usts.pojo.FormData;

/**
 * 分页Bean
 *
 * @Author: ${朱朝阳}
 * @Date: 2019/7/17 13:18
 */

public class PageOrSize {

    /**
     * 根据ID查询
     */
    private Integer id;

    /**
     * 查询长度
     */
    private int size;

    /**
     * 起始位置
     */
    private int start;

    /**
     * 表格名称
     */
    private String tableName;

    /**
     * 用户口令
     */
    private String userToken;

    /**
     * 包裹FormData
     */
    private FormData formData;

    /**
     * 当前角色
     */
    private String role;

    /**
     * Form表单传递时间
     * 未经过JSON序列化会出现String到后台Date接受 400 HTTP
     */
    private String strTime;

    private String realName;

    private String college;

    private String marjor;


    public PageOrSize() {

    }

    public PageOrSize(Integer id, int size, int start, String tableName,
                      String userToken, FormData formData, String role, String strTime,
                      String realName, String college, String marjor) {
        this.id = id;
        this.size = size;
        this.start = start;
        this.tableName = tableName;
        this.userToken = userToken;
        this.formData = formData;
        this.role = role;
        this.strTime = strTime;
        this.realName = realName;
        this.college = college;
        this.marjor = marjor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public FormData getFormData() {
        return formData;
    }

    public void setFormData(FormData formData) {
        this.formData = formData;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMarjor() {
        return marjor;
    }

    public void setMarjor(String marjor) {
        this.marjor = marjor;
    }

    @Override
    public String toString() {
        return "PageOrSize{" +
                "id=" + id +
                ", size=" + size +
                ", start=" + start +
                ", tableName='" + tableName + '\'' +
                ", userToken='" + userToken + '\'' +
                ", formData=" + formData +
                ", role='" + role + '\'' +
                ", strTime='" + strTime + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
