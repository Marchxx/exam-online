package com.march.main.dao;

import com.march.main.entity.ExamRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.march.main.params.GetRecordListParam;
import com.march.main.vo.RecordExamListVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
public interface ExamRecordMapper extends BaseMapper<ExamRecord> {

    List<RecordExamListVo> getExamRecord(GetRecordListParam param);
}
