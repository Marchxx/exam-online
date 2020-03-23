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
 * @since 2020-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Question对象", description="")
public class Question extends Model<Question> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "题目id，主键")
    @TableId(value = "question_id", type = IdType.AUTO)
    private Integer questionId;

    @ApiModelProperty(value = "题目名称")
    private String questionName;

    @ApiModelProperty(value = "题目分数")
    private Integer questionScore;

    @ApiModelProperty(value = "题型种类，如单选，多选")
    private Integer questionTypeId;

    @ApiModelProperty(value = "题目分类，如Java，c++")
    private Integer questionCategoryId;


    @Override
    protected Serializable pkVal() {
        return this.questionId;
    }

}
