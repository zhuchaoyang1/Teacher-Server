package cn.usts.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员维护所有页面模板
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Templete {

    private Integer id;

    private String path;

    private String fsize;

    private String fname;

    private String templete;

    private String tableName;

    // 新增字段  用于教务编写文件名称  和  上面的fname不冲突   前端显示的时候显示的是下面这个name
    private String uploadName;

}
