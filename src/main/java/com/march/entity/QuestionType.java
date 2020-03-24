package com.march.entity;

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
@ApiModel(value="QuestionType对象", description="")
public class QuestionType extends Model<QuestionType> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "题目类型Id，主键")
    @TableId(value = "question_type_id", type = IdType.AUTO)
    private Integer questionTypeId;

    @ApiModelProperty(value = "题目类型名称")
    private String questionTypeName;


    @Override
    protected Serializable pkVal() {
        return this.questionTypeId;
    }

}
