package com.march.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.march.common.utils.R;
import com.march.main.dao.RecordAnswerMapper;
import com.march.main.dao.RecordOptionMapper;
import com.march.main.entity.ExamRecord;
import com.march.main.dao.ExamRecordMapper;
import com.march.main.entity.RecordAnswer;
import com.march.main.entity.RecordOption;
import com.march.main.service.ExamRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.march.main.service.RecordAnswerService;
import com.march.main.vo.RecordAnsVo;
import com.march.main.vo.RecordExamVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    RecordAnswerMapper answerMapper;

    @Autowired
    RecordOptionMapper optionMapper;

    @Override
    public int addExamRecord(ExamRecord record) {
        boolean insert = record.insert();
        //返回插入后的自增长主键
        if (insert)
            return record.getRecordId();
        return -1;
    }

    @Override
    public List<ExamRecord> getAllExamRecord() {
        ExamRecord examRecord = new ExamRecord();
        return examRecord.selectAll();
    }

    @Override
    public Integer getExamId(Integer id) {
        ExamRecord examRecord = new ExamRecord();
        examRecord.setRecordId(id);
        return examRecord.selectById().getExamId();
    }

    /**
     * 根据记录rid和题目题号列表，返回答案列表
     * ArrayList的add方法，每次加入一个引用，需要在循环里new一个对象
     *
     * @param integers
     * @return
     */
    @Override
    public List<RecordAnsVo> getRecordOptAns(Integer rId, List<Integer> integers) {
        //构建返回List<RecordAnsVo>对象和RecordAnsVo
        List<RecordAnsVo> voList = new ArrayList<>();
        for (Integer qid : integers) {
            RecordAnsVo vo = new RecordAnsVo();
            //设置题号
            vo.setQuestionId(qid);
            //查询出选项答案,并拼接成字符串
            List<RecordOption> selectList = optionMapper.selectList(new QueryWrapper<RecordOption>()
                    .eq("record_id", rId)
                    .eq("question_id", qid));
            StringBuffer sb = new StringBuffer();
            for (RecordOption option : selectList) {
                sb.append(option.getIdx());
            }
            if ("".equals(sb.toString()))
                vo.setAnswer(null);
            else
                vo.setAnswer(sb.toString());
            //加入返回列表
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<RecordAnsVo> getRecordOtherAns(Integer rId, List<Integer> integers) {
        //构建返回List<RecordAnsVo>对象和RecordAnsVo
        List<RecordAnsVo> voList = new ArrayList<>();
        //遍历题号列表
        for (Integer qid : integers) {
            RecordAnsVo vo = new RecordAnsVo();
            //设置题号
            vo.setQuestionId(qid);
            //查询出记录答案
            RecordAnswer answer = answerMapper.selectOne(new QueryWrapper<RecordAnswer>()
                    .eq("record_id", rId)
                    .eq("question_id", qid));
            if (answer == null)
                vo.setAnswer(null);
            else
                vo.setAnswer(answer.getAnswer());
            //加入返回列表
            voList.add(vo);
        }
        return voList;
    }
}
