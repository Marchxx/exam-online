package com.march.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.march.common.utils.R;
import com.march.main.dao.ExamQuestionMapper;
import com.march.main.entity.*;
import com.march.main.dao.ExamMapper;
import com.march.main.params.AddExamParam;
import com.march.main.params.MultipleParam;
import com.march.main.params.SubmitExamParam;
import com.march.main.service.ExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.march.main.service.QuestionService;
import com.march.main.vo.ExamQuestionOpts;
import com.march.main.vo.ExamQuestionOthers;
import com.march.main.vo.OptionContentVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {

    @Autowired
    ExamQuestionMapper examQuestionMapper;

    @Autowired
    QuestionService questionService;

    @Override
    public Exam findExamById(Integer id) {
        Exam exam = new Exam();
        User user = new User();
        exam.setExamId(id);
        //查出考试记录
        Exam getExam = exam.selectById();
        if (getExam != null) {
            //查出用户姓名
            User getUser = user.selectOne(new QueryWrapper<User>().eq("user_id", getExam.getExamCreatorId()));
            getExam.setExamCreatorName(getUser.getName());
            return getExam;
        }
        return null;
    }

    @Override
    public List<Exam> findAllExam() {
        Exam exam = new Exam();
        User user = new User();
        List<Exam> exams = exam.selectAll();
        for (Exam e : exams) {
            User one = user.selectOne(new QueryWrapper<User>().eq("user_id", e.getExamCreatorId()));
            if (one != null) e.setExamCreatorName(one.getName());
        }
        return exams;
    }

    @Override
    public boolean addExam(AddExamParam addExamParam) {
        try {
            //新增试卷
            Exam exam = addExamParam.getExam();
            exam.insert();
            //MybatisPlus获取insert后的自增主键id
            int generateKey = exam.getExamId();
            //新增试卷题目列表
            List<ExamQuestion> questionList = addExamParam.getQuestionList();
            for (ExamQuestion question : questionList) {
                //设置question的exam_id,为自动生成的generateKey
                question.setExamId(generateKey);
                question.insert();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param id
     * @param flag=1则查询已发布的考试信息
     * @return
     */
    @Override
    public List<Exam> findExamListByTid(Integer id, Integer flag) {
        //初始化查询对象和返回对象
        Exam exam = new Exam();
        User user = new User();
        //设置查询条件,若flag=1则加上is_issued=1条件
        QueryWrapper<Exam> wrapper = new QueryWrapper<>();
        wrapper.eq("exam_creator_id", id);
        if (flag == 1) wrapper.eq("is_issued", 1);
        //查出考试列表Exams，遍历添加到ExamVolist中
        List<Exam> exams = exam.selectList(wrapper);
        for (Exam e : exams) {
            //将Exam实体类中数据库不存在的exam_creator_name加上
            User one = user.selectOne(new QueryWrapper<User>().eq("user_id", id));
            if (one != null) e.setExamCreatorName(one.getName());
        }
        return exams;
    }

    @Override
    public boolean delExamInfo(Integer id) {
        Exam exam = new Exam();
        return exam.deleteById(id);
    }

    @Override
    public boolean delExamQList(Integer id) {
        ExamQuestion question = new ExamQuestion();
        QueryWrapper<ExamQuestion> wrapper = new QueryWrapper<>();
        wrapper.eq("exam_id", id);
        return question.delete(wrapper);
    }


    /**
     * 根据考试id，对试题类型进行分类
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, List<Integer>> sortByTypeId(Integer id) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= 4; i++) {
            //分别查询对应4种类型的题目 题号列表
            List<Integer> list = examQuestionMapper.sortByTypeId(id, i);
            map.put(Integer.toString(i), list);
        }
        return map;
    }

    /**
     * 根据题目编号列表，查询试卷中的试题，并返回
     *
     * @param integers
     * @return
     * @flag 0不带ans，1请求标准答案
     */
    @Override
    public List<ExamQuestionOpts> getQuestionOpts(List<Integer> integers, Integer flag) {
        List<ExamQuestionOpts> voList = new ArrayList<>();
        for (Integer qid : integers) {
            ExamQuestionOpts vo = new ExamQuestionOpts();
            //查出问题
            Question q = questionService.getQuestionById(qid);
            vo.setQuestionId(q.getQuestionId());
            vo.setQuestionName(q.getQuestionName());
            vo.setQuestionScore(q.getQuestionScore());
            //查出选项
            List<OptionContentVo> optList = questionService.getOptionDetailById(qid);
            //flag=0，则设置选项答案为空
            if (flag == 0) {
                for (OptionContentVo contentVo : optList) {
                    contentVo.setAnswer(null);
                }
            }
            vo.setOptList(optList);
            //加入到返回list中
            voList.add(vo);
        }
        return voList;
    }

    /**
     * flag 0不带ans，1请求标准答案
     *
     * @param integers
     * @return
     */
    @Override
    public List<ExamQuestionOthers> getQuestionOthers(List<Integer> integers, Integer flag) {
        List<ExamQuestionOthers> voList = new ArrayList<>();
        for (Integer qid : integers) {
            ExamQuestionOthers vo = new ExamQuestionOthers();
            //查出问题
            Question q = questionService.getQuestionById(qid);
            vo.setQuestionId(q.getQuestionId());
            vo.setQuestionName(q.getQuestionName());
            vo.setQuestionScore(q.getQuestionScore());
            //查出问题标准答案
            vo.setAnswer(questionService.getOthersAnswer(qid));
            //flag=0，则设置答案为空
            if (flag == 0) {
                vo.setAnswer(null);
            }
            voList.add(vo);
        }
        return voList;
    }

    /**
     * 1.判断试题是否正确，计算总分 2.保存学生答案记录
     *
     * @param param
     * @return
     */
    @Override
    public int judgeAndSaveAns(SubmitExamParam param) {
        //获取自增长生成的主键roleId
        int autoKey = param.getExamRecord().getRecordId();
        int score = 0;
        //处理单选题
        for (RecordOption option : param.getRadiosAnsList()) {
            option.setRecordId(autoKey);
            option.insert();
            if (questionService.checkOptAnswer(option.getQuestionId(), option.getIdx().toString())) {
                //答案正确,查出问题得分
                Question question = questionService.getQuestionById(option.getQuestionId());
                score += question.getQuestionScore();
            }
        }
        //处理多选题
        for (MultipleParam multipleParam : param.getMultiplesAnsList()) {
            //设置插入记录对象
            RecordOption recordOption = new RecordOption();
            recordOption.setRecordId(autoKey);
            recordOption.setQuestionId(multipleParam.getQuestionId());
            //拼接多选题答案，同时保存答案记录
            StringBuffer sb = new StringBuffer();
            if (multipleParam.getIdx1() == 1) {
                recordOption.setIdx(1);
                recordOption.insert();
                sb.append(String.valueOf(1));
            }
            if (multipleParam.getIdx2() == 1) {
                recordOption.setIdx(2);
                recordOption.insert();
                sb.append(String.valueOf(2));
            }
            if (multipleParam.getIdx3() == 1) {
                recordOption.setIdx(3);
                recordOption.insert();
                sb.append(String.valueOf(3));
            }
            if (multipleParam.getIdx4() == 1) {
                recordOption.setIdx(4);
                recordOption.insert();
                sb.append(String.valueOf(4));
            }
            if (questionService.checkOptAnswer(multipleParam.getQuestionId(), sb.toString())) {
                //答案正确,查出问题得分
                Question question = questionService.getQuestionById(multipleParam.getQuestionId());
                score += question.getQuestionScore();
            }
        }
        //处理判断题
        for (RecordAnswer answer : param.getJudgesAnsList()) {
            answer.setRecordId(autoKey);
            answer.insert();
            if (questionService.getOthersAnswer(answer.getQuestionId()).equals(answer.getAnswer())) {
                Question question = questionService.getQuestionById(answer.getQuestionId());
                score += question.getQuestionScore();
            }
        }
        //处理填空题
        for (RecordAnswer answer : param.getFillBlanksList()) {
            answer.setRecordId(autoKey);
            answer.insert();
            if (questionService.getOthersAnswer(answer.getQuestionId()).equals(answer.getAnswer())) {
                Question question = questionService.getQuestionById(answer.getQuestionId());
                score += question.getQuestionScore();
            }
        }
        System.out.println("score=" + score);
        return score;
    }
}