package com.march.common.office.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class QuestionExcel implements java.io.Serializable {

    @Excel(name = "题目描述")
    @NotBlank(message = "该字段不能为空")
    private String questionName;

    @Excel(name = "题型分类")
    private int questionTypeId;

    @Excel(name = "选项A")
    private String a;

    @Excel(name = "选项B")
    private String b;

    @Excel(name = "选项C")
    private String c;

    @Excel(name = "选项D")
    private String d;

    @Excel(name = "正确答案")
    private String answer;

    @Excel(name = "题目分数")
    private int questionScore;

    @Excel(name = "科目类别")
    private String questionCategoryName;
}
