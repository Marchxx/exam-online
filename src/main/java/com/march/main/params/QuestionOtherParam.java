package com.march.main.params;

import com.march.main.entity.Question;
import com.march.main.entity.QuestionAnswer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "填空判断题参数")
public class QuestionOtherParam {

    @ApiModelProperty(value = "问题基本信息")
    Question question;

    @ApiModelProperty(value = "标准答案")
    String answer;
}
