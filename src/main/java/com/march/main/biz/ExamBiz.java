package com.march.main.biz;

import com.march.common.enums.CodeEnum;
import com.march.common.utils.R;
import com.march.main.entity.Exam;
import com.march.main.entity.ExamQuestion;
import com.march.main.params.ExamParam;
import com.march.main.service.ClassService;
import com.march.main.service.ExamService;
import com.march.main.vo.ClassTeaVo;
import com.march.main.vo.ExamStuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamBiz {

    @Autowired
    ExamService examService;

    @Autowired
    ClassService classService;

    public R findExamById(Integer id) {
        Exam exam = examService.findExamById(id);
        if (exam != null) return R.success().put("data", exam);
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R addExam(ExamParam examParam) {
        //新建试卷
        boolean flag1 = examService.addExam(examParam);
        if (flag1) return R.success();
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R findExamListBySid(Integer sid) {
        try {
            //初始化返回类型，进行封装
            List<ExamStuVo> voList = new ArrayList<>();
            //先根据学生s_id查询所在班级信息列表
            List<ClassTeaVo> classTeaVoList = classService.getTeaInfo(sid);
            if (classTeaVoList != null) {
                for (ClassTeaVo result : classTeaVoList) {
                    //取出t_id，查询(已发布flag=1)的考试列表
                    List<Exam> exams = examService.findExamListByTid(result.getUserId(), 1);
                    //分别保存进vo并添加到voList中
                    ExamStuVo vo = new ExamStuVo();
                    vo.setClassName(result.getClassName());
                    vo.setCreatorId(result.getUserId());
                    vo.setCreatorName(result.getName());
                    vo.setExamList(exams);
                    voList.add(vo);
                }
                return R.success().put("data", voList);
            }
            return R.error(CodeEnum.OTHER_ERROR);
        } catch (Exception e) {
            return R.error(CodeEnum.OTHER_ERROR);
        }
    }

    public R findExamListByTid(Integer id) {
        List<Exam> exams = examService.findExamListByTid(id, 0);
        return R.success().put("data", exams);
    }

    public R findAllExam() {
        List<Exam> exams = examService.findAllExam();
        return R.success().put("data", exams);
    }

    public R updateExamDetail(ExamParam examParam) {
        //1.先更新考试信息
        Exam e=examParam.getExam();
        boolean b1 = e.updateById();
        //2.删除原来的题目列表
        boolean b2 = examService.delExamQList(e.getExamId());
        //3.插入新保存的题目
        List<ExamQuestion> qList=examParam.getQuestionList();
        for (ExamQuestion q : qList) {
            //设置上对应的eId
            q.setExamId(e.getExamId());
            q.insert();
        }
        if(b1&&b2)return R.success("更新考试信息成功");
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R delExamDetail(Integer id) {
         if(examService.delExamInfo(id)&&examService.delExamQList(id)){
             return R.success("删除考试信息成功");
         }
         return R.error(CodeEnum.OTHER_ERROR);
    }
}
