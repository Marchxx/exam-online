package com.march.main.dao;

import com.march.main.entity.ExamQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
public interface ExamQuestionMapper extends BaseMapper<ExamQuestion> {

    List<Integer> sortByTypeId(@Param("id") Integer id,@Param("i") int i);
}
