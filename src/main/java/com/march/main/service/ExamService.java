package com.march.main.service;

import com.march.main.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.march.main.params.ExamParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
public interface ExamService extends IService<Exam> {

    Exam findExamById(Integer id);

    boolean addExam(ExamParam examParam);

    List<Exam> findExamListByTid(Integer id,Integer flag);

    List<Exam> findAllExam();
}
