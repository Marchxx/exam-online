package com.march.main.controller;


import com.march.common.utils.R;
import com.march.main.biz.ClassBiz;
import com.march.main.entity.Class;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author March
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/class")
@Api(tags = "班级管理")
public class ClassController {

    @Autowired
    ClassBiz classBiz;

    @GetMapping("/all")
    @ApiOperation(value = "获取所有班级列表")
    public R getAllClass() {
        return classBiz.getAllClass();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据tea_id查询,所含的班级列表")
    public R getClassById(@PathVariable("id") Integer id) {
        return classBiz.getClassById(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "新建/更新班级信息")
    public R addOrUpdateClass(Class c) {
        return classBiz.addOrUpdateClass(c);
    }

    @GetMapping("/del/{id}")
    @ApiOperation(value = "根据c_id,删除班级")
    public R deleteById(@PathVariable("id") Integer id) {
        return classBiz.deleteById(id);
    }

    @GetMapping("/stuonList/{id}")
    @ApiOperation(value = "根据t_id,查询已包含的学生列表")
    public R getStuListById(@PathVariable("id") Integer id) {
        return classBiz.getStuListById(id);
    }

    @GetMapping("/stunotList/{id}")
    @ApiOperation(value = "根据c_id,查询未包含的学生列表")
    public R getStuNotListById(@PathVariable("id") Integer id) {
        return classBiz.getStuNotListById(id);
    }

    @GetMapping("/stu/add")
    @ApiOperation(value = "根据t_id和stu_id,批量增加学生")
    public R getAddStu(Integer tId, Integer[] stus) {
        return classBiz.getAddStu(tId,stus);
    }

    @GetMapping("/stu/del")
    @ApiOperation(value = "根据t_id和stu_id,批量删除学生")
    public R getDelStu(Integer tId, Integer[] stus) {
        return classBiz.getDelStu(tId,stus);
    }

    @GetMapping("/tea")
    @ApiOperation(value = "根据stu_id,查询所在班级,老师信息")
    public R getTeaInfo(Integer sId) {
        return classBiz.getTeaInfo(sId);
    }
}

