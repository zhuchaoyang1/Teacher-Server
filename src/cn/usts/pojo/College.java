package cn.usts.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学院
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class College {

    private Integer id;

    private String collegeName;

    public College(String collegeName) {
        this.collegeName = collegeName;
    }

}
