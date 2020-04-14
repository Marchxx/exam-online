package com.march.main.biz;

import com.march.common.enums.CodeEnum;
import com.march.common.utils.R;
import com.march.main.dao.ClassMapper;
import com.march.main.entity.Class;
import com.march.main.service.ClassService;
import com.march.main.vo.ClassStuVo;
import com.march.main.vo.ClassTeaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassBiz {

    @Autowired
    ClassService classService;

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

    public R getStuListById(Integer id) {
        boolean b = classService.isExist(id);
        if (b) {
            List<ClassStuVo> voList = classService.getStuListById(id);
            return R.success().put("data", voList);
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R getAddStu(Integer cId, Integer[] stus) {
        int i = classService.getAddStu(cId, stus);
        return R.success().put("data", i);
    }

    public R getDelStu(Integer cId, Integer[] stus) {
        int i = classService.getDelStu(cId, stus);
        return R.success().put("data", i);
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

