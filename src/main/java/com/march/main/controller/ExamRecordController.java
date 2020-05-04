package com.march.main.controller;


import com.march.common.utils.R;
import com.march.main.biz.ExamRecordBiz;
import com.march.main.entity.ExamRecord;
import com.march.main.params.GetRecordListParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/condition")
    @ApiOperation(value = "根据条件查询考试记录列表" +
            "1班级名称，2参加考试学生用户名 3出卷教师tId 4参与考试学生sId")
    public R getExamRecord(@RequestBody GetRecordListParam param){
        return recordBiz.getExamRecord(param);
    }

    @ApiOperation(value = "根据recordId查询作答情况")
    @GetMapping("/Info/{id}")
    public R getRecordInfoById(@PathVariable("id") Integer id) {
        return recordBiz.getRecordInfoById(id);
    }
}

