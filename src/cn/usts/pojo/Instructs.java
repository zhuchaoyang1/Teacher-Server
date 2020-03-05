package cn.usts.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 通知公告
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Instructs {

    private Long id;
    /**
     * 发送者ID
     */
    private String senderId;
    /**
     * 接受者ID
     */
    private String receiverId;
    /**
     * 发送内容
     */
    private String content;
    /**
     * 发送时间
     */
    private String nowDate;

    /**
     * 标题
     */
    private String title;

    private String userToken;

    private SysUser sysUser;

    private Integer pageNo;

    private Integer pageSize;

    private Integer uId;

    private String realName;

    private Integer role;

    /**
     * 通告状态
     * 0:未签收
     * 1:签收
     */
    private Integer status;

}
