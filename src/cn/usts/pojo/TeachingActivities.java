package cn.usts.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 教学任务表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeachingActivities {

    private Integer id;

    // 上传人ID
    private Integer uId;

    // 名字
    private String descName;

    // 日期
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date deteTime;

    // 参加人
    private String attentPeople;

    private String filename;

    private String filepath;

    // 上传人所在学院
    private String college;

    // 上传人专业
    private String marjor;

    // List 用于前后端使用
    private List<String> attendPersons;

    private String userToken;

    private Integer start;

    private Integer size;

}
