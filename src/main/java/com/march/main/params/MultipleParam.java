package com.march.main.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@ApiModel(value = "多选题提交参数")
public class MultipleParam {

    @ApiModelProperty(value = "对应的题目id,主键")
    private Integer questionId;

    @ApiModelProperty(value = "选项序号idx1,默认0")
    private Integer idx1 = 0;

    @ApiModelProperty(value = "选项序号idx2,默认0")
    private Integer idx2 = 0;

    @ApiModelProperty(value = "选项序号idx3,默认0")
    private Integer idx3 = 0;

    @ApiModelProperty(value = "选项序号idx4,默认0")
    private Integer idx4 = 0;
}
