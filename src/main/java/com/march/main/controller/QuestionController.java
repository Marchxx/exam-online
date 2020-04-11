package com.march.main.controller;


import com.march.common.utils.R;
import com.march.main.biz.QuestionBiz;
import com.march.main.params.GetQuestListParam;
import com.march.main.params.QuestionOptParam;
import com.march.main.params.QuestionOtherParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "根据问题typeId和categoryId" +
            "请求问题列表(1Java，2C/C++，3数据库，4计算机网络，5数据结构，6操作系统),(可不填)" +
            "请求问题列表(1单选，2多选，3判断，4填空),(必填)")
    @GetMapping("/list")
    public R getListById(GetQuestListParam param){
        return questionBiz.getListById(param);
    }

    @ApiOperation(value = "根据选择题id，获取选项具体内容")
    @GetMapping("/opts/{id}")
    public R getOptionById(@PathVariable("id") Integer id){
        return questionBiz.getOptionById(id);
    }

    @ApiOperation(value = "更新,选择题")
    @PostMapping("/update/opt")
    public R addOrUpdateOpts(@RequestBody QuestionOptParam param){
        System.out.println(param.getQuestion());
        System.out.println(param.getOptionList());
        return questionBiz.addOrUpdateOpts(param);
    }

    @ApiOperation(value = "更新,填空/判断题")
    @PostMapping("/update/other")
    public R addOrUpdateother(@RequestBody QuestionOtherParam param){
        System.out.println(param.getQuestion());
        System.out.println(param.getAnswer());
        return questionBiz.addOrUpdateother(param);
    }

    @ApiOperation(value = "批量删除,选择题")
    @GetMapping("/del/opt")
    public R delOptByIds(Integer ids[]){
        return questionBiz.delOptByIds(ids);
    }

    @ApiOperation(value = "批量删除,填空/判断题")
    @GetMapping("/del/other")
    public R delOtherByIds(Integer ids[]){
        return questionBiz.delOtherByIds(ids);
    }
}

