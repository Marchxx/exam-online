package com.march.main.params;

import com.march.main.entity.Question;
import com.march.main.entity.QuestionAnswer;
import com.march.main.entity.QuestionOption;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "选择题参数")
public class QuestionOptParam {

    //问题实体类
    Question question;
    //选项实体类 列表
    List<QuestionOption> optionList;
}
