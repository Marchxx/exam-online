package com.march.main.dao;

import com.march.main.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.march.main.vo.OptionContentVo;
import com.march.main.vo.QuestionAnswerVo;
import com.march.main.vo.QuestionOptionVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
public interface QuestionMapper extends BaseMapper<Question> {

    List<QuestionOptionVo> getOpsListByTypeId(Integer id);

    List<QuestionAnswerVo> getOthersListByTypeId(Integer id);

    List<OptionContentVo> getOptionDetailById(Integer id);
}
