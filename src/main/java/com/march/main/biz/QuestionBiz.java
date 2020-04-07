package com.march.main.biz;

import com.march.common.enums.CodeEnum;
import com.march.common.utils.R;
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

    public R getListByTypeId(Integer id) {
        //区分选择和判断填空。返回的vo不同,执行的sql语句不同
        if (id == 1 || id == 2) {
            List<QuestionOptionVo> voList = questionService.getOpsListByTypeId(id);
            return R.success().put("data", voList);
        } else if (id == 3 || id == 4) {
            List<QuestionAnswerVo> voList = questionService.getOthersListByTypeId(id);
            return R.success().put("data", voList);
        } else {
            //返回传参错误
            return R.error(CodeEnum.PARAM_ERROR);
        }
    }

    public R getOptionById(Integer id) {
        //获取选项具体内容
        List<OptionContentVo> voList = questionService.getOptionDetailById(id);
        if (!voList.isEmpty()) {
            return R.success().put("data", voList);
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }
}
