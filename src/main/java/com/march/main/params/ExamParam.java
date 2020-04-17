package com.march.main.params;

import com.march.main.entity.Exam;
import com.march.main.entity.ExamQuestion;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.util.List;

@Data
@ApiModel(value = "新建试卷参数")
public class ExamParam {

    //试卷实体类
    Exam exam;
    //题目列表
    List<ExamQuestion> questionList;
}
