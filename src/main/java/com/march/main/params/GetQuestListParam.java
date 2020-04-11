package com.march.main.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询问题列表参数")
public class GetQuestListParam {

    @ApiModelProperty(value = "请求问题列表(1Java，2C/C++，3数据库，4计算机网络，5数据结构，6操作系统)")
    private Integer categoryId;

    @ApiModelProperty(value = "请求问题列表(1单选，2多选，3判断，4填空)")
    private Integer typeId;

}
