package com.march.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.march.main.dao.ClassMapper;
import com.march.main.entity.Class;
import com.march.main.entity.ClassStu;
import com.march.main.service.ClassService;
import com.march.main.vo.ClassStuVo;
import com.march.main.vo.ClassTeaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author March
 * @since 2020-04-12
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    @Autowired
    ClassMapper classMapper;

    @Override
    public List<Class> getAllClass() {
        Class c = new Class();
        return c.selectAll();
    }

    @Override
    public List<Class> getClassById(Integer id) {
        Class c = new Class();
        QueryWrapper<Class> wrapper = new QueryWrapper();
        wrapper.eq("teacher_id", id);
        return c.selectList(wrapper);
    }

    @Override
    public List<ClassStuVo> getStuListById(Integer id) {
        return classMapper.getStuListById(id);
    }

    @Override
    public int getAddStu(Integer cId, Integer[] stus) {
        ClassStu classStu = new ClassStu();
        int count = 0;
        for (Integer stu : stus) {
            classStu.setClassId(cId);
            classStu.setStuId(stu);
            boolean insert = classStu.insert();
            if (insert)
                count++;
        }
        return count;
    }

    @Override
    public List<ClassTeaVo> getTeaInfo(Integer sId) {
        return classMapper.getTeaInfo(sId);
    }

    @Override
    public boolean isExist(Integer id) {
        Class c = new Class();
        c.setClassId(id);
        Class select = c.selectById();
        if (select != null) return true;
        else return false;
    }

    @Override
    public List<ClassStuVo> getStuNotListById(Integer id) {
        return classMapper.getStuNotListById(id);
    }

    @Override
    public int getDelStu(Integer cId, Integer[] stus) {
        ClassStu classStu = new ClassStu();
        int count = 0;
        for (Integer stu : stus) {
            QueryWrapper<ClassStu> wrapper = new QueryWrapper<>();
            wrapper.eq("class_id", cId);
            wrapper.eq("stu_id", stu);
            boolean delete = classStu.delete(wrapper);
            if (delete)
                count++;
        }
        return count;
    }
}
