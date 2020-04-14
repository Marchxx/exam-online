package com.march.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.march.main.entity.Class;
import com.march.main.vo.ClassStuVo;
import com.march.main.vo.ClassTeaVo;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author March
 * @since 2020-04-12
 */
public interface ClassService extends IService<Class> {


    List<Class> getAllClass();

    List<Class> getClassById(Integer id);

    List<ClassStuVo> getStuListById(Integer id);

    int getAddStu(Integer cId, Integer[] stus);

    int getDelStu(Integer cId, Integer[] stus);

    List<ClassStuVo> getStuNotListById(Integer id);

    boolean isExist(Integer id);

    List<ClassTeaVo> getTeaInfo(Integer id);
}
