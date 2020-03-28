package com.march.main.entity;

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
@ApiModel(value="QuestionOption对象", description="")
public class QuestionOption extends Model<QuestionOption> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "题目id,主键")
    private Integer questionId;

    @ApiModelProperty(value = "选项序号idx,主键")
    private Integer idx;

    @ApiModelProperty(value = "选项描述")
    private String content;

    @ApiModelProperty(value = "答案,1正确,0错误")
    private Integer answer;


    @Override
    protected Serializable pkVal() {
        return this.questionId;
    }

}
