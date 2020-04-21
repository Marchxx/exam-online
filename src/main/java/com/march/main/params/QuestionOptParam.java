package com.march.main.params;

import com.march.main.entity.Question;
import com.march.main.entity.QuestionAnswer;
import com.march.main.entity.QuestionOption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "选择题参数")
public class QuestionOptParam {

    @ApiModelProperty(value = "问题基本信息")
    Question question;

    @ApiModelProperty(value = "选项列表")
    List<QuestionOption> optionList;
}
