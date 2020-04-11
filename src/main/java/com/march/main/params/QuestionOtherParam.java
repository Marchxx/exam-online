package com.march.main.params;

import com.march.main.entity.Question;
import com.march.main.entity.QuestionAnswer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "填空判断题参数")
public class QuestionOtherParam {

    //问题实体类
    Question question;
    //答案实体类
    QuestionAnswer answer;
}
