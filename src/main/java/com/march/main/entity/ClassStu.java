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
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ClassStu对象", description="")
public class ClassStu extends Model<ClassStu> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "班级-学生表id，流水号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "班级id")
    private Integer classId;

    @ApiModelProperty(value = "学生用户id")
    private Integer stuId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
