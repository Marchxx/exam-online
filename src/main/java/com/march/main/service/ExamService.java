package com.march.main.service;

import com.march.main.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.march.main.params.AddExamParam;
import com.march.main.params.SubmitExamParam;
import com.march.main.vo.ExamQuestionOpts;
import com.march.main.vo.ExamQuestionOthers;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
public interface ExamService extends IService<Exam> {

    Exam findExamById(Integer id);

    boolean addExam(AddExamParam addExamParam);

    List<Exam> findExamListByTid(Integer id, Integer flag);

    List<Exam> findAllExam();

    boolean delExamInfo(Integer id);

    boolean delExamQList(Integer id);

    Map<String, List<Integer>> sortByTypeId(Integer id);

    List<ExamQuestionOpts> getQuestionOpts(List<Integer> integers, Integer flag);

    List<ExamQuestionOthers> getQuestionOthers(List<Integer> integers, Integer flag);

    Map<Integer, Integer> judgeAndSaveAns(SubmitExamParam param);

    boolean issueExamById(Integer eId);
}
