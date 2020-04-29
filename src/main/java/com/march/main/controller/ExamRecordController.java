package com.march.main.controller;


import com.march.common.utils.R;
import com.march.main.biz.ExamRecordBiz;
import com.march.main.entity.ExamRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/examRecord")
@Api(tags = "考试记录管理")
public class ExamRecordController {

    @Autowired
    ExamRecordBiz recordBiz;

    @GetMapping("/all")
    @ApiOperation(value = "查询全部考试记录")
    public R getAllExamRecord() {
        return recordBiz.getAllExamRecord();
    }

    @ApiOperation(value = "根据recordId查询作答情况")
    @GetMapping("/Info/{id}")
    public R getRecordInfoById(@PathVariable("id") Integer id) {
        return recordBiz.getRecordInfoById(id);
    }
}

