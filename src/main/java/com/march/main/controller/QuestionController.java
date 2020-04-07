package com.march.main.controller;


import com.march.common.utils.R;
import com.march.main.biz.QuestionBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
@RequestMapping("/question")
@Api(tags = "试题资源管理模块")
public class QuestionController {

    @Autowired
    QuestionBiz questionBiz;

    @ApiOperation(value = "根据问题Type_id，请求列表(1单选，2多选，3判断，4填空)")
    @GetMapping("/list/{id}")
    public R getListByTypeId(@PathVariable("id") Integer id){
        return questionBiz.getListByTypeId(id);
    }

    @ApiOperation(value = "根据选择题目id，获取选项/答案内容")
    @GetMapping("/opts/{id}")
    public R getOptionById(@PathVariable("id") Integer id){
        return questionBiz.getOptionById(id);
    }

}

