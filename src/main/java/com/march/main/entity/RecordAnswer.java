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
@ApiModel(value="RecordAnswer对象", description="")
public class RecordAnswer extends Model<RecordAnswer> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "某学生某考试记录id,主键",hidden = true)
    private Integer recordId;

    @ApiModelProperty(value = "考试记录对应的题目id,主键")
    private Integer questionId;

    @ApiModelProperty(value = "作答答案")
    private String answer;


    @Override
    protected Serializable pkVal() {
        return this.recordId;
    }

}
