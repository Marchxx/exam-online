package com.march.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@ApiModel(value = "QuestionCategory对象", description = "")
public class QuestionCategory extends Model<QuestionCategory> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题目分类Id，主键")
    @TableId(value = "question_category_id", type = IdType.AUTO)
    @JsonProperty("qCategoryId")
    private Integer questionCategoryId;

    @ApiModelProperty(value = "题目分类名称")
    @JsonProperty("qCategoryName")
    private String questionCategoryName;


    @Override
    protected Serializable pkVal() {
        return this.questionCategoryId;
    }

}
