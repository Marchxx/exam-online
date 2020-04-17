package com.march.main.vo;
import com.march.main.entity.Exam;
import lombok.Data;
import java.util.List;

@Data
public class ExamStuVo {
    //班级名称
    String className;
    //创建者id
    Integer creatorId;
    //创建者姓名
    String creatorName;
    //试卷列表
    List<Exam> examList;
}
