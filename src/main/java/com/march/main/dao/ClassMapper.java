package com.march.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.march.main.entity.Class;
import com.march.main.vo.ClassStuVo;
import com.march.main.vo.ClassTeaVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author March
 * @since 2020-04-12
 */
public interface ClassMapper extends BaseMapper<Class> {

    List<ClassStuVo> getStuListById(Integer id);

    List<ClassStuVo> getStuNotListById(Integer id);

    List<ClassTeaVo> getTeaInfo(Integer sId);
}
