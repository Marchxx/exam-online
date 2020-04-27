package com.march.main.service;

import com.march.main.entity.ExamRecord;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
