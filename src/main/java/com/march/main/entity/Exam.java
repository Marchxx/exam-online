package com.march.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @author March
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Exam对象", description = "")
public class Exam extends Model<Exam> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考试表id,主键,自增")
    @TableId(value = "exam_id", type = IdType.AUTO)
    private Integer examId;

    @ApiModelProperty(value = "考试名称")
    private String examName;

    @ApiModelProperty(value = "考试描述")
    private String examDesc;

    @ApiModelProperty(value = "试卷总分")
    private Integer examScoreTotal;

    @ApiModelProperty(value = "及格分数")
    private Integer examScorePass;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "考试开始时间yyyy-MM-dd HH:mm:ss")
    private LocalDateTime examTimeStart;

    @ApiModelProperty(value = "考试限制时间")
    private Integer examTimeLimit;

    @ApiModelProperty(value = "考试创建者id")
    private Integer examCreatorId;

    @TableField(exist = false)
    @ApiModelProperty(value = "考试创建者姓名,数据库表中不存在", hidden = true)
    private String examCreatorName;

    @ApiModelProperty(value = "0未发布,1已发布", hidden = true)
    private Integer isIssued;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间yyyy-MM-dd HH:mm:ss", hidden = true)
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间yyyy-MM-dd HH:mm:ss", hidden = true)
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.examId;
    }

}
