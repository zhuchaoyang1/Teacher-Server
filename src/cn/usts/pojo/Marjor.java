package cn.usts.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 专业
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelTarget("marjorExcel")
public class Marjor {

    private Integer id;
    /**
     * 专业名
     */
    @Excel(name = "专业名称", orderNum = "0")
    private String marjorName;
    /**
     * 所属学院
     */
    private String collegeName;
    /**
     * College ID
     */
    private Integer collegeId;

    public Marjor(String collegeName) {
        this.collegeName = collegeName;
    }

}
