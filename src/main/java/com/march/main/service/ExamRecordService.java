package com.march.main.service;

import com.march.common.utils.R;
import com.march.main.entity.ExamRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.march.main.vo.RecordAnsVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
public interface ExamRecordService extends IService<ExamRecord> {

    int addExamRecord(ExamRecord record);

    List<ExamRecord> getAllExamRecord();

    Integer getExamId(Integer id);

    List<RecordAnsVo> getRecordOptAns(Integer rId, List<Integer> integers);

    List<RecordAnsVo> getRecordOtherAns(Integer rId, List<Integer> integers);
}
