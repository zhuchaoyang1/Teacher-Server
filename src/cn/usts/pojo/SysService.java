package cn.usts.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/8/1 23:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysService {

    /**
     * ID
     */
    private int id;

    /**
     * UID对应的评论者ID
     */
    private int uId;

    /**
     * 评论文字
     */
    private String textarea;

    /**
     * 星数
     */
    private int rateCount;

    private String userToken;

    private boolean switchs;

    /**
     * 一对一包含
     */
    private SysUser sysUser;

    // 用于分页
    private Integer start;

    private Integer size;

}
