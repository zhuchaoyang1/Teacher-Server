package cn.usts.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
