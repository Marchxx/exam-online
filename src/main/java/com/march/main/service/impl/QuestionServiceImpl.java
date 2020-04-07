package com.march.main.service.impl;

import com.march.main.entity.Question;
import com.march.main.dao.QuestionMapper;
import com.march.main.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.march.main.vo.OptionContentVo;
import com.march.main.vo.QuestionAnswerVo;
import com.march.main.vo.QuestionOptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public List<QuestionOptionVo> getOpsListByTypeId(Integer id) {
        return questionMapper.getOpsListByTypeId(id);
    }

    @Override
    public List<QuestionAnswerVo> getOthersListByTypeId(Integer id) {
        return questionMapper.getOthersListByTypeId(id);
    }

    @Override
    public List<OptionContentVo> getOptionDetailById(Integer id) {
        return questionMapper.getOptionDetailById(id);
    }
}
