package com.march.main.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.march.common.enums.CodeEnum;
import com.march.common.utils.R;
import com.march.main.entity.QuestionAnswer;
import com.march.main.entity.QuestionOption;
import com.march.main.params.GetQuestListParam;
import com.march.main.params.QuestionOptParam;
import com.march.main.params.QuestionOtherParam;
import com.march.main.service.QuestionAnswerService;
import com.march.main.service.QuestionOptionService;
import com.march.main.service.QuestionService;
import com.march.main.vo.OptionContentVo;
import com.march.main.vo.QuestionAnswerVo;
import com.march.main.vo.QuestionOptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionBiz {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionOptionService qOptService;

    @Autowired
    QuestionAnswerService qAnsService;

    public R getOptionById(Integer id) {
        //获取选项具体内容
        List<OptionContentVo> voList = questionService.getOptionDetailById(id);
        if (!voList.isEmpty()) {
            return R.success().put("data", voList);
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R delOptByIds(Integer[] ids) {
        if (questionService.delOptByIds(ids) == ids.length)
            return R.success("题目批量删除成功");
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R delOtherByIds(Integer[] ids) {
        if (questionService.delOtherByIds(ids) == ids.length)
            return R.success("题目批量删除成功");
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R getListById(GetQuestListParam param) {
        //区分选择和判断填空。返回的vo不同,执行的sql语句不同
        if (param.getTypeId() != null) {
            int typeId = param.getTypeId();
            if (typeId == 1 || typeId == 2) {
                //处理选择题
                List<QuestionOptionVo> voList = questionService.getOpsListById(param);
                for (QuestionOptionVo vo : voList) {
                    //根据题目Id查询出，对应的选项list，一起封装进Vo对象
                    List<OptionContentVo> optList = questionService.getOptionDetailById(vo.getQuestionId());
                    vo.setOptList(optList);
                }
                return R.success().put("data", voList);
            } else if (typeId == 3 || typeId == 4) {
                //处理填空/判断题
                List<QuestionAnswerVo> voList = questionService.getOthersListById(param);
                return R.success().put("data", voList);
            }
        }
        //返回传参错误
        return R.error(CodeEnum.PARAM_ERROR);
    }

    public R addOrUpdateOpts(QuestionOptParam param) {
        try {
            boolean flag1 = questionService.updateById(param.getQuestion());
            //由qID和idx联合主键(不能使用MP的updateById方法)，更新选项表信息
            //用接口实现类写QueryWrapper语句更新
            for (QuestionOption option : param.getOptionList()) {
                option.setQuestionId(param.getQuestion().getQuestionId());
            }
            boolean flag2 = qOptService.addOrUpdateOpts(param.getOptionList());
            if (flag1 && flag2) {
                return R.success("题目信息更新成功");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return R.error(CodeEnum.OTHER_ERROR);
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R addOrUpdateother(QuestionOtherParam param) {
        try {
            boolean flag1 = questionService.updateById(param.getQuestion());
            //根据param字段,创建answer对象(qId,answer)
            QuestionAnswer answer=new QuestionAnswer();
            answer.setQuestionId(param.getQuestion().getQuestionId());
            answer.setAnswer(param.getAnswer());
            boolean flag2 = qAnsService.updateById(answer);
            if (flag1 && flag2) {
                return R.success("题目信息更新成功");
            }
            return R.error(CodeEnum.OTHER_ERROR);
        } catch (Exception e) {
            return R.error(CodeEnum.OTHER_ERROR);
        }
    }
}
