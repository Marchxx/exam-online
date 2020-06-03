package com.march.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value="ExamRecord对象", description="")
public class ExamRecord extends Model<ExamRecord> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "流水号考试记录id,主键,自增",hidden = true)
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    @ApiModelProperty(value = "考试场次id")
    private Integer examId;

    @ApiModelProperty(value = "参与考试用户id")
    private Integer examJoinerId;

    @ApiModelProperty(value = "考试花费时间")
    private Integer examTimeCost;

    @ApiModelProperty(value = "试卷总分",hidden = true)
    private Integer examScore;

    @ApiModelProperty(value = "单选题总分",hidden = true)
    private Integer examScore1;

    @ApiModelProperty(value = "多选题总分",hidden = true)
    private Integer examScore2;

    @ApiModelProperty(value = "判断题总分",hidden = true)
    private Integer examScore3;

    @ApiModelProperty(value = "填空题总分",hidden = true)
    private Integer examScore4;


    @Override
    protected Serializable pkVal() {
        return this.recordId;
    }

}
