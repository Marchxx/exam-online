package com.march.main.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.march.common.enums.CodeEnum;
import com.march.common.utils.R;
import com.march.main.entity.Question;
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

    public R addOrUpdateOpts(QuestionOptParam param) {
        try {
            //根据qId查询是否存在该试题
            Integer qId = param.getQuestion().getQuestionId();
            if (qId != null && questionService.getQuestionById(qId) != null) {
                if (questionService.updateOpts(param))
                    return R.success("题目信息更新成功");
            } else {
                if (questionService.addOpts(param))
                    return R.success("题目信息新建成功");
            }
            return R.error(CodeEnum.OTHER_ERROR);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return R.error(CodeEnum.OTHER_ERROR);
        }
    }

    public R addOrUpdateOther(QuestionOtherParam param) {
        try {
            //根据qId查询是否存在该试题
            Integer qId = param.getQuestion().getQuestionId();
            if (qId != null && questionService.getQuestionById(qId) != null) {
                if (questionService.updateOther(param))
                    return R.success("题目信息更新成功");
            } else {
                if (questionService.addOther(param))
                    return R.success("题目信息新建成功");
            }
            return R.error(CodeEnum.OTHER_ERROR);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return R.error(CodeEnum.OTHER_ERROR);
        }
    }
}