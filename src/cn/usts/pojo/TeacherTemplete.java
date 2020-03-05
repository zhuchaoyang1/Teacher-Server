package cn.usts.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教学模板
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherTemplete {

    private Integer id;

    private String filename;

    private String filepath;

    private String desct;

    private String college;

    private String userToken;

    private Integer uId;

    /**
     * 院长姓名
     */
    private String uName;

    private Integer start;

    private Integer size;
}
