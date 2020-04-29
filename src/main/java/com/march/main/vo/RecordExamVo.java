package com.march.main.vo;

import lombok.Data;

import java.util.List;

@Data
public class RecordExamVo {
    //单选题
    private List<RecordAnsVo> radios;
    //多选题
    private List<RecordAnsVo> multiples;
    //判断题
    private List<RecordAnsVo> judges;
    //填空题
    private List<RecordAnsVo> fillBlanks;
}
