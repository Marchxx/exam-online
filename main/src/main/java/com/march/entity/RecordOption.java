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
@ApiModel(value="RecordOption对象", description="")
public class RecordOption extends Model<RecordOption> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "某学生某考试记录id,主键")
    private Integer recordId;

    @ApiModelProperty(value = "对应的题目id,主键")
    private Integer questionId;

    @ApiModelProperty(value = "选项序号idx,主键")
    private Integer idx;


    @Override
    protected Serializable pkVal() {
        return this.recordId;
    }

}
