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
@ApiModel(value="Class对象", description="")
public class Class extends Model<Class> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "班级id,主键,自增(注册时可不填，更新必填)")
    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    @ApiModelProperty(value = "班级名称")
    private String className;

    @ApiModelProperty(value = "教师id")
    private Integer teacherId;


    @Override
    protected Serializable pkVal() {
        return this.classId;
    }

}
