package com.march.main.biz;

import com.march.common.enums.CodeEnum;
import com.march.common.utils.R;
import com.march.main.entity.Exam;
import com.march.main.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamBiz {

    @Autowired
    ExamService examService;

    public R findExamById(Integer id) {
        Exam exam=examService.findExamById(id);
        return R.success().put("data",exam);
    }
}
