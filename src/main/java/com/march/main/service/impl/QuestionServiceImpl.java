package com.march.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.march.main.dao.QuestionAnswerMapper;
import com.march.main.dao.QuestionOptionMapper;
import com.march.main.entity.Question;
import com.march.main.dao.QuestionMapper;
import com.march.main.params.GetQuestListParam;
import com.march.main.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.march.main.vo.OptionContentVo;
import com.march.main.vo.QuestionAnswerVo;
import com.march.main.vo.QuestionOptionVo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionOptionMapper questionOptionMapper;

    @Autowired
    QuestionAnswerMapper questionAnswerMapper;

    @Override
    public List<OptionContentVo> getOptionDetailById(Integer id) {
        return questionMapper.getOptionDetailById(id);
    }

    @Override
    public int delOptByIds(Integer[] ids) {//删选择题
        //删除，问题表记录
        List<Integer> list = new ArrayList<>();
        for (Integer id : ids) {
            list.add(id);
        }
        int batchIds = questionMapper.deleteBatchIds(list);
        //删除，问题选项表的记录
        QueryWrapper wrapper=new QueryWrapper();
        for (Integer id : ids) {
            wrapper.eq("question_id",id);
            System.out.println(wrapper);
            int i = questionOptionMapper.delete(wrapper);
        }
        return batchIds;
    }

    @Override
    public int delOtherByIds(Integer[] ids) {//删(填空/判断)题
        //删除，问题表记录
        List<Integer> list = new ArrayList<>();
        for (Integer id : ids) {
            list.add(id);
        }
        int batchIds = questionMapper.deleteBatchIds(list);
        //删除，问题答案表的记录
        QueryWrapper wrapper=new QueryWrapper();
        for (Integer id : ids) {
            wrapper.eq("question_id",id);
            System.out.println(wrapper);
            int i = questionAnswerMapper.delete(wrapper);
        }
        return batchIds;
    }

    @Override
    public List<QuestionOptionVo> getOpsListById(GetQuestListParam param) {
        return questionMapper.getOpsListById(param);
    }

    @Override
    public List<QuestionAnswerVo> getOthersListById(GetQuestListParam param) {
        return questionMapper.getOthersListById(param);
    }
}
