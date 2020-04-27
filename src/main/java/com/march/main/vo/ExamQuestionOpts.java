package com.march.main.vo;

import lombok.Data;

import java.util.List;

@Data
public class ExamQuestionOpts {

    private Integer questionId;
    private String questionName;
    private Integer questionScore;
    private List<OptionContentVo> optList;
}
