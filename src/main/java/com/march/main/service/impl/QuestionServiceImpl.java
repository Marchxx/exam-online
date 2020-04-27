package com.march.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.march.main.dao.QuestionAnswerMapper;
import com.march.main.dao.QuestionOptionMapper;
import com.march.main.entity.Question;
import com.march.main.dao.QuestionMapper;
import com.march.main.entity.QuestionAnswer;
import com.march.main.entity.QuestionOption;
import com.march.main.params.GetQuestListParam;
import com.march.main.params.QuestionOptParam;
import com.march.main.params.QuestionOtherParam;
import com.march.main.service.QuestionOptionService;
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

    @Autowired
    QuestionOptionService qOptService;

    //查询填空/判断答案
    @Override
    public String getOthersAnswerById(Integer id) {
        QuestionAnswer qa = new QuestionAnswer();
        qa.setQuestionId(id);
        return qa.selectById().getAnswer();
    }

    //查询选择题答案
    @Override
    public List<Integer> getOptAnswerById(Integer id) {
        QuestionOption qo = new QuestionOption();
        List<Integer> voList = questionMapper.getOptAnswerById(id);
        return voList;
    }

    //返回选项题干
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
        for (Integer id : ids) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("question_id", id);
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
        for (Integer id : ids) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("question_id", id);
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

    @Override
    public Question getQuestionById(Integer id) {
        Question question = new Question();
        question.setQuestionId(id);
        return question.selectById();
    }

    @Override
    public boolean addOther(QuestionOtherParam param) {
        boolean insert1 = param.getQuestion().insert();
        //insert后会自动保存自增的id值，再根据param字段,创建answer对象(qId,answer)
        QuestionAnswer answer = new QuestionAnswer();
        answer.setQuestionId(param.getQuestion().getQuestionId());
        answer.setAnswer(param.getAnswer());
        boolean insert2 = answer.insert();
        if (insert1 && insert2)
            return true;
        return false;
    }

    @Override
    public boolean updateOther(QuestionOtherParam param) {
        boolean update1 = param.getQuestion().updateById();
        //根据param字段,创建answer对象(qId,answer)
        QuestionAnswer answer = new QuestionAnswer();
        answer.setQuestionId(param.getQuestion().getQuestionId());
        answer.setAnswer(param.getAnswer());
        boolean update2 = answer.updateById();
        if (update1 && update2)
            return true;
        return false;
    }

    @Override
    public boolean updateOpts(QuestionOptParam param) {
        boolean update1 = param.getQuestion().updateById();
        //根据param字段,创建option对象(qId,idx,content,answer)
        for (QuestionOption option : param.getOptionList()) {
            option.setQuestionId(param.getQuestion().getQuestionId());
        }
        boolean update2 = qOptService.UpdateOpts(param.getOptionList());
        if (update1 && update2)
            return true;
        return false;
    }

    @Override
    public boolean addOpts(QuestionOptParam param) {
        boolean insert1 = param.getQuestion().insert();
        //insert后会自动保存自增的id值，再根据param字段,创建option对象(qId,idx,content,answer)
        for (QuestionOption option : param.getOptionList()) {
            option.setQuestionId(param.getQuestion().getQuestionId());
        }
        boolean insert2 = qOptService.addOpts(param.getOptionList());
        if (insert1 && insert2)
            return true;
        return false;
    }
}