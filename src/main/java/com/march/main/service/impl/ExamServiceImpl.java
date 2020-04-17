package com.march.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.march.main.entity.*;
import com.march.main.dao.ExamMapper;
import com.march.main.entity.Class;
import com.march.main.params.ExamParam;
import com.march.main.service.ExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Exam findExamById(Integer id) {
        Exam exam = new Exam();
        exam.setExamId(id);
        return exam.selectById();
    }

    @Override
    public List<Exam> findAllExam() {
        Exam exam = new Exam();
        User user = new User();
        List<Exam> exams = exam.selectAll();
        for (Exam e : exams) {
            User one = user.selectOne(new QueryWrapper<User>().eq("user_id", e.getExamCreatorId()));
            e.setExamCreatorName(one.getName());
        }
        return exams;
    }

    @Override
    public boolean addExam(ExamParam examParam) {
        try {
            //新增试卷
            Exam exam = examParam.getExam();
            exam.insert();
            //MybatisPlus获取insert后的自增主键id
            int generateKey = exam.getExamId();
            //新增试卷题目列表
            List<ExamQuestion> questionList = examParam.getQuestionList();
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
        if(flag==1) wrapper.eq("is_issued",1);
        //查出考试列表Exams，遍历添加到ExamVolist中
        List<Exam> exams = exam.selectList(wrapper);
        for (Exam e : exams) {
            //将Exam实体类中数据库不存在的exam_creator_name加上
            User one = user.selectOne(new QueryWrapper<User>().eq("user_id", id));
            e.setExamCreatorName(one.getName());
        }
        return exams;
    }
}
