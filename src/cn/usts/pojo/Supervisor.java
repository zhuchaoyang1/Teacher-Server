package cn.usts.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 教学督导报
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supervisor {

    private Integer id;

    private Integer uId;

    private String descName;

    private String filename;

    private String filepath;

    private String college;

    private String marjor;

    private Integer start;

    private Integer size;

    private String userToken;

    /**
     * 是否本院下所有老师可看
     * 1：本院所有老师可以看
     * 2：指定人员可以查看
     */
    private Boolean isAllCanLook;

    private String canLookPersonIds;

    private String canLookPersonNames;

    private Integer[] canLookPersonIdsArray; // 不直接保存在数据库中 而是通过保存在中间表中

    private List<SysUser> sysUser;

    private Integer role;

}
