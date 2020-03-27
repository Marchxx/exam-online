package com.march.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@ApiModel(value="QuestionAnswer对象", description="")
public class QuestionAnswer extends Model<QuestionAnswer> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "题目id(填空，判断),主键")
    private Integer questionId;

    @ApiModelProperty(value = "答案描述")
    private String answer;


    @Override
    protected Serializable pkVal() {
        return this.questionId;
    }

}
