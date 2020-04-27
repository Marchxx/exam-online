package com.march.main.service.impl;

import com.march.main.entity.ExamRecord;
import com.march.main.dao.ExamRecordMapper;
import com.march.main.service.ExamRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
@Service
public class ExamRecordServiceImpl extends ServiceImpl<ExamRecordMapper, ExamRecord> implements ExamRecordService {

    @Override
    public int addExamRecord(ExamRecord record) {
        boolean insert = record.insert();
        //返回插入后的自增长主键
        if (insert)
            return record.getRecordId();
        return -1;
    }
}
