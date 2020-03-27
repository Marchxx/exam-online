package com.xiyou.common.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author tangcan
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserLog对象", description = "")
public class UserLog extends Model<UserLog> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户操作日志的id")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "操作描述")
    private String operation;

    @ApiModelProperty(value = "操作系统")
    private String os;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "请求结果")
    private String result;

    @ApiModelProperty(value = "用户ip")
    private String ip;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "操作时间")
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

}
