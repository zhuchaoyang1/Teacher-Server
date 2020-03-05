package cn.usts.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/16 23:19
 */

public class FormData {

    /**
     * ID
     */
    private Integer pId;
    /**
     * 类别
     */
    private String category;
    /**
     * 项目名称
     */
    private String title;
    /**
     * 所获奖励或支持名称
     */
    private String support;
    /**
     * 时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date personalTime;
    /**
     * 等级 OR 级别
     */
    private String personalLevel;
    /**
     * 授予部门
     */
    private String department;
    /**
     * 附件地址
     */
    private String filepath;
    /**
     * 交互表名称
     */
    private String tableName;

    /**
     * 用户外键
     */
    private Integer uID;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * FormDate
     */
    private SysUser sysUser;

    private String userToken;

    private String marjor;

    private String college;

    public FormData() {
    }

    public FormData(Integer pId, String category, String title, String support,
                    Date personalTime, String personalLevel, String department,
                    String filepath, String tableName, Integer uID, String filename,
                    SysUser sysUser, String college, String marjor) {
        this.pId = pId;
        this.category = category;
        this.title = title;
        this.support = support;
        this.personalTime = personalTime;
        this.personalLevel = personalLevel;
        this.department = department;
        this.filepath = filepath;
        this.tableName = tableName;
        this.uID = uID;
        this.filename = filename;
        this.sysUser = sysUser;
        this.college = college;
        this.marjor = marjor;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }


    public Date getPersonalTime() {
        return personalTime;
    }

    public void setPersonalTime(Date personalTime) {
        this.personalTime = personalTime;
    }

    public String getPersonalLevel() {
        return personalLevel;
    }

    public void setPersonalLevel(String personalLevel) {
        this.personalLevel = personalLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getuID() {
        return uID;
    }

    public void setuID(Integer uID) {
        this.uID = uID;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getMarjor() {
        return marjor;
    }

    public void setMarjor(String marjor) {
        this.marjor = marjor;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "FormData{" +
                "pId=" + pId +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", support='" + support + '\'' +
                ", personalTime=" + personalTime +
                ", personalLevel='" + personalLevel + '\'' +
                ", department='" + department + '\'' +
                ", filepath='" + filepath + '\'' +
                ", tableName='" + tableName + '\'' +
                ", uID=" + uID +
                ", filename='" + filename + '\'' +
                ", sysUser=" + sysUser +
                '}';
    }
}
