package com.march.main.params;

import com.march.main.entity.Exam;
import com.march.main.entity.ExamQuestion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
@ApiModel(value = "新建试卷参数")
public class AddExamParam {

    @ApiModelProperty(value = "试卷基本信息")
    Exam exam;

    @ApiModelProperty(value = "题目列表")
    List<ExamQuestion> questionList;
}
