package com.march.main.controller;



import com.march.common.utils.R;
import com.march.main.biz.ExamBiz;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    ExamBiz examBiz;

    @ApiOperation(value = "根据id，查询考试记录")
    @GetMapping("/{id}")
    public R findExamById(@PathVariable("id") Integer id){
        return examBiz.findExamById(id);
    }

}

