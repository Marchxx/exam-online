package com.march.main.biz;

import com.march.common.enums.CodeEnum;
import com.march.common.utils.R;
import com.march.main.entity.Exam;
import com.march.main.entity.ExamQuestion;
import com.march.main.params.AddExamParam;
import com.march.main.params.SubmitExamParam;
import com.march.main.service.ClassService;
import com.march.main.service.ExamRecordService;
import com.march.main.service.ExamService;
import com.march.main.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamBiz {

    @Autowired
    ExamService examService;

    @Autowired
    ClassService classService;

    @Autowired
    ExamRecordService examRecordService;

    public R findExamById(Integer id) {
        Exam exam = examService.findExamById(id);
        if (exam != null) return R.success().put("data", exam);
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R addExam(AddExamParam addExamParam) {
        //新建试卷
        boolean flag1 = examService.addExam(addExamParam);
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

    public R updateExamInfo(AddExamParam addExamParam) {
        //1.先更新考试信息
        Exam e = addExamParam.getExam();
        boolean b1 = e.updateById();
        //2.删除原来的题目列表
        boolean b2 = examService.delExamQList(e.getExamId());
        //3.插入新保存的题目
        List<ExamQuestion> qList = addExamParam.getQuestionList();
        for (ExamQuestion q : qList) {
            //设置上对应的eId
            q.setExamId(e.getExamId());
            q.insert();
        }
        if (b1 && b2) return R.success("更新考试信息成功");
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R delExamInfo(Integer id) {
        if (examService.delExamInfo(id) && examService.delExamQList(id)) {
            return R.success("删除考试信息成功");
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R getExamDetailById(Integer id) {
        //创建ExamVo返回对象
        ExamDetailVo detailVo = new ExamDetailVo();
        Exam exam = examService.findExamById(id);
        //存放每份试卷的不同类型的题目的分布
        Map<String, List<Integer>> map = new HashMap<>();
        if (exam != null) {
            map = examService.sortByTypeId(id);
            List<ExamQuestionOpts> radios = examService.getQuestionOpts(map.get("1"));
            List<ExamQuestionOpts> multiples = examService.getQuestionOpts(map.get("2"));
            List<ExamQuestionOthers> judges = examService.getQuestionOthers(map.get("3"));
            List<ExamQuestionOthers> fillBlanks = examService.getQuestionOthers(map.get("4"));
            //封装返回对象
            detailVo.setExam(exam);
            detailVo.setRadios(radios);
            detailVo.setMultiples(multiples);
            detailVo.setJudges(judges);
            detailVo.setFillBlanks(fillBlanks);
            return R.success().put("data", detailVo);
        }
        return R.error(CodeEnum.EXAM_NOT_EXIST);
    }

    /**
     * 提交试卷
     *
     * @param param
     * @return
     */
    public R submitExam(SubmitExamParam param) {
        try {
            //先生成一条考试记录，总分默认为0，返回自增长主键
            int autoKey = examRecordService.addExamRecord(param.getExamRecord());
            param.getExamRecord().setRecordId(autoKey);
            //1.根据答案判分 2.设置生成的roleId，插入对应的记录表 3.返回计算得出的分数
            int i = examService.judgeAndSaveAns(param);
            param.getExamRecord().setExamScore(i);
            //更新试卷记录分数
            boolean update = param.getExamRecord().updateById();
            return R.success().put("data", "提交试卷成功!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return R.error(CodeEnum.OTHER_ERROR);
        }
    }
}
