package com.march.main.controller;



import com.march.common.utils.R;
import com.march.main.biz.ExamBiz;
import com.march.main.params.AddExamParam;
import com.march.main.params.SubmitExamParam;
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
@RequestMapping("/exam")
@Api(tags = "考试模块")
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
        return examBiz.delExamInfo(id);
    }

    @ApiOperation(value = "更新考试,时间格式yyyy-MM-dd HH:mm:ss(examId必填)")
    @PostMapping("/update")
    public R updateExamInfo(@RequestBody AddExamParam addExamParam){
        return examBiz.updateExamInfo(addExamParam);
    }

    @ApiOperation(value = "新建考试,时间格式yyyy-MM-dd HH:mm:ss(examId不用填)")
    @PostMapping("/add")
    public R addExam(@RequestBody AddExamParam addExamParam){
        System.out.println(addExamParam);
        return examBiz.addExam(addExamParam);
    }

    @ApiOperation(value = "开始考试,获取试卷")
    @GetMapping("/start/{id}")
    public R getExamDetailById(@PathVariable("id") Integer id){
        return examBiz.getExamDetailById(id);
    }

    @ApiOperation(value = "结束考试,提交试卷")
    @PostMapping("/submit")
    public R submitExam(@RequestBody SubmitExamParam param){
        System.out.println(param);
        return examBiz.submitExam(param);
    }
}

