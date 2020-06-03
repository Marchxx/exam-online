package com.march.main.vo;

import lombok.Data;

import java.util.List;

@Data
public class QuestionOptionVo {

    private Integer questionId;
    private String questionName;
    private Integer questionScore;

    private Integer questionTypeId;
    private String questionTypeName;
    private Integer questionCategoryId;
    private String questionCategoryName;
    //选项内容，List
    private List<OptionContentVo> optList;
}
