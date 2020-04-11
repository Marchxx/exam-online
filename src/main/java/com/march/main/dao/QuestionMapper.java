package com.march.main.dao;

import com.march.main.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.march.main.params.GetQuestListParam;
import com.march.main.vo.OptionContentVo;
import com.march.main.vo.QuestionAnswerVo;
import com.march.main.vo.QuestionOptionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
public interface QuestionMapper extends BaseMapper<Question> {

    List<OptionContentVo> getOptionDetailById(Integer id);

    List<QuestionOptionVo> getOpsListById(GetQuestListParam param);

    List<QuestionAnswerVo> getOthersListById(GetQuestListParam param);
}
