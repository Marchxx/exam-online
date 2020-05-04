package com.march.main.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询考试记录列表参数")
public class GetRecordListParam {

    @ApiModelProperty(value = "班级名称")
    private String className;

    @ApiModelProperty(value = "参与考试学生的用户名")
    private String userName;

    @ApiModelProperty(value = "出卷教师id")
    private Integer tId;

    @ApiModelProperty(value = "参与考试的学生id")
    private Integer sId;
}
