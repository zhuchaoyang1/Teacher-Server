package cn.usts.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发布教学任务表单形式： 名字、附件（不限格式）  （权限：给同学院人看）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeachingTask {

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

}
