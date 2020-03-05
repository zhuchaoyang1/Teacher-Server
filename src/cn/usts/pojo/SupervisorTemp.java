package cn.usts.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 中间表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupervisorTemp {

    private Integer id;

    private Integer uId;

    private Integer sId;

    public SupervisorTemp(Integer sId) {
        this.sId = sId;
    }

}
