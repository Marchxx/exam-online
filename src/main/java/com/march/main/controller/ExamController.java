package com.march.main.controller;



import com.march.common.utils.R;
import com.march.main.biz.ExamBiz;
import com.march.main.entity.Exam;
import com.march.main.entity.ExamQuestion;
import com.march.main.params.ExamParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/exam")
@Api(tags = "考试管理模块")
public class ExamController {

    @Autowired
    ExamBiz examBiz;

    @ApiOperation(value = "根据tea_id,查询(全部)考试列表")
    @GetMapping("/tea/{id}")
    public R findExamListByTid(@PathVariable("id") Integer id){
        return examBiz.findExamListByTid(id);
    }

    @ApiOperation(value = "根据stu_id,查询(已发布)的考试列表")
    @GetMapping("/stu/{id}")
    public R findExamListBySid(@PathVariable("id") Integer id){
        return examBiz.findExamListBySid(id);
    }

    @ApiOperation(value = "全部考试列表")
    @GetMapping("/list/all")
    public R findAllExam(){
        return examBiz.findAllExam();
    }

    @ApiOperation(value = "根据eId,删除考试信息")
    @GetMapping("/delete/{id}")
    public R delExamInfo(@PathVariable("id") Integer id){
        return examBiz.delExamDetail(id);
    }

    @ApiOperation(value = "更新考试,时间格式yyyy-MM-dd HH:mm:ss(examId必填)")
    @PostMapping("/update")
    public R updateExamDetail(@RequestBody ExamParam examParam){
        return examBiz.updateExamDetail(examParam);
    }

    @ApiOperation(value = "新建考试,时间格式yyyy-MM-dd HH:mm:ss(examId不用填)")
    @PostMapping("/add")
    public R addExam(@RequestBody ExamParam examParam){
        System.out.println(examParam);
        return examBiz.addExam(examParam);
    }

}

