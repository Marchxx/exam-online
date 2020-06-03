package com.march.main.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.march.common.enums.CodeEnum;
import com.march.common.utils.R;
import com.march.main.entity.Class;
import com.march.main.entity.ClassStu;
import com.march.main.entity.User;
import com.march.main.service.ClassService;
import com.march.main.service.ClassStuService;
import com.march.main.service.UserService;
import com.march.main.vo.ClassStuVo;
import com.march.main.vo.ClassTeaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassBiz {

    @Autowired
    ClassService classService;

    @Autowired
    UserService userService;

    @Autowired
    ClassStuService classStuService;

    public R getAllClass() {
        List<Class> classList = classService.getAllClass();
        return R.success().put("data", classList);
    }

    public R getClassById(Integer id) {
        List<Class> classList = classService.getClassById(id);
        return R.success().put("data", classList);
    }

    public R addOrUpdateClass(Class c) {
        if (classService.saveOrUpdate(c)) {
            return R.success();
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R deleteById(Integer id) {
        if (classService.removeById(id)) {
            return R.success();
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R getStuListById(Integer tId) {
        //根据tId查找出对应的班级cId
        List<Class> list = classService.getClassById(tId);
        if (list != null && list.size() > 0) {
            int cId = list.get(0).getClassId();
            boolean b = classService.isExist(cId);
            if (b) {
                List<ClassStuVo> voList = classService.getStuListById(cId);
                return R.success().put("data", voList);
            }
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R getAddStu(Integer tId, Integer[] stus) {
        //查询出用户存在性与权限
        User user = userService.findUserById(stus[0]);
        if (user == null) return R.error(CodeEnum.USER_NOT_EXIST);
        if (user.getRoleId() != 3) return R.error(CodeEnum.AUTHORIZATION_FAILED);//权限错误
        //根据tId查找出对应的班级cId
        List<Class> list = classService.getClassById(tId);
        if (list != null && list.size() > 0) {
            int cId = list.get(0).getClassId();
            //判断学生是否已存在于该班级
            ClassStu stu = classStuService.getOneByIds(cId, stus[0]);
            if (stu != null) return R.error(CodeEnum.STU_ALREADY_EXIST);//学生用户已存在该班级
            int i = classService.getAddStu(cId, stus);
            return R.success().put("count", i);
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R getDelStu(Integer tId, Integer[] stus) {
        //根据tId查找出对应的班级cId
        List<Class> list = classService.getClassById(tId);
        if (list != null && list.size() > 0) {
            int cId = list.get(0).getClassId();
            int i = classService.getDelStu(cId, stus);
            return R.success().put("count", i);
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R getStuNotListById(Integer id) {
        boolean b = classService.isExist(id);
        if (b) {
            List<ClassStuVo> voList = classService.getStuNotListById(id);
            return R.success().put("data", voList);
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R getTeaInfo(Integer sId) {
        List<ClassTeaVo> voList = classService.getTeaInfo(sId);
        return R.success().put("data", voList);
    }
}

