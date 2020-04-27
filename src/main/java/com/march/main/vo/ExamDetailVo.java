package com.march.main.vo;

import com.march.main.entity.Exam;
import lombok.Data;

import java.util.List;

@Data
public class ExamDetailVo {

    //考试实体类
    private Exam exam;
    //单选题
    private List<ExamQuestionOpts> radios;
    //多选题
    private List<ExamQuestionOpts> multiples;
    //判断题
    private List<ExamQuestionOthers> judges;
    //填空题
    private List<ExamQuestionOthers> fillBlanks;
}
