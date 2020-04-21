package com.march.main.params;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@Api(tags = "查询用户列表参数")
public class GetUserListParam {

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "学号")
    private String userName;

    @ApiModelProperty(value = "姓名")
    private String name;
}
