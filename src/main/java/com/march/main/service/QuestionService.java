package com.march.main.service;

import com.march.main.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.march.main.vo.OptionContentVo;
import com.march.main.vo.QuestionAnswerVo;
import com.march.main.vo.QuestionOptionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
public interface QuestionService extends IService<Question> {

    List<QuestionOptionVo> getOpsListByTypeId(Integer id);

    List<QuestionAnswerVo> getOthersListByTypeId(Integer id);

    List<OptionContentVo> getOptionDetailById(Integer id);
}
