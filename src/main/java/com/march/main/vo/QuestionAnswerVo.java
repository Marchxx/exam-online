package com.march.main.vo;

import lombok.Data;


@Data
public class QuestionAnswerVo {

    private Integer questionId;
    private String questionName;
    private String answer;
    private Integer questionScore;

    private Integer questionTypeId;
    private String questionTypeName;
    private Integer questionCategoryId;
    private String questionCategoryName;
}
