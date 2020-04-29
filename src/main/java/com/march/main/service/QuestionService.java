package com.march.main.service;

import com.march.main.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.march.main.params.GetQuestListParam;
import com.march.main.params.QuestionOptParam;
import com.march.main.params.QuestionOtherParam;
import com.march.main.vo.OptionContentVo;
import com.march.main.vo.QuestionAnswerVo;
import com.march.main.vo.QuestionOptionVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
public interface QuestionService extends IService<Question> {

    boolean checkOptAnswer(Integer qId, String ans);

    List<OptionContentVo> getOptionDetailById(Integer id);

    String getOthersAnswer(Integer id);

    Question getQuestionById(Integer id);

    int delOptByIds(Integer[] ids);

    int delOtherByIds(Integer[] ids);

    List<QuestionOptionVo> getOpsListById(GetQuestListParam param);

    List<QuestionAnswerVo> getOthersListById(GetQuestListParam param);

    boolean addOther(QuestionOtherParam param);

    boolean updateOther(QuestionOtherParam param);

    boolean updateOpts(QuestionOptParam param);

    boolean addOpts(QuestionOptParam param);
}