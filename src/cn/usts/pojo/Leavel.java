package cn.usts.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 有教务账号上传等级
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Leavel {

    private Integer id;

    // 教务ID
    private Integer jwId;

    // 教务所在学院
    private String college;

    // 备用字段  用于保存不同模块  避免后期的级别会因为不同模块而不同
    private String modules;

    // 父组件名称
    private String faleavel;

    // 子组件名称
    private String sonleavel;


}
