package com.march.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value="ExamQuestion对象", description="")
public class ExamQuestion extends Model<ExamQuestion> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "流水号id,主键,自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "考试id")
    private Integer examId;

    @ApiModelProperty(value = "题目id")
    private Integer questionId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
