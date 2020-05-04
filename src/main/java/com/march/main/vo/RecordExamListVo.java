package com.march.main.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RecordExamListVo {

    private Integer recordId;

    private Integer examId;

    private String examName;

    private String examDesc;

    @JsonProperty("tId")
    private Integer teacherId;

    @JsonProperty("tName")
    private String teacherName;

    @JsonProperty("sUserName")
    private String userName;

    @JsonProperty("sName")
    private String name;

    private Integer examScore;

    private Integer examScoreTotal;

    private Date examTimeStart;

    private Integer examTimeCost;


}
