package com.march.main.params;

import com.march.main.entity.ExamRecord;
import com.march.main.entity.RecordAnswer;
import com.march.main.entity.RecordOption;
import com.march.main.vo.ExamQuestionOpts;
import com.march.main.vo.ExamQuestionOthers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "提交试卷参数")
public class SubmitExamParam {

    @ApiModelProperty(value = "考试记录")
    private ExamRecord examRecord;

    @ApiModelProperty(value = "单选题答案列表")
    private List<RecordOption> radiosAnsList;

    @ApiModelProperty(value = "多选题答案列表")
    private List<RecordOption> multiplesAnsList;

    @ApiModelProperty(value = "判断题答案列表")
    private List<RecordAnswer> judgesAnsList;

    @ApiModelProperty(value = "填空题答案列表")
    private List<RecordAnswer> fillBlanksList;
}
