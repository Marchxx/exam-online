package com.march.main.biz;

import com.march.common.enums.CodeEnum;
import com.march.common.utils.R;
import com.march.main.entity.ExamRecord;
import com.march.main.params.GetRecordListParam;
import com.march.main.service.ExamRecordService;
import com.march.main.service.ExamService;
import com.march.main.vo.RecordAnsVo;
import com.march.main.vo.RecordExamListVo;
import com.march.main.vo.RecordExamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamRecordBiz {

    @Autowired
    ExamRecordService recordService;

    @Autowired
    ExamService examService;

    public R getRecordInfoById(Integer rId) {
        try {
            //先根据recordId获取到examId
            Integer examId = recordService.getExamId(rId);
            //查询对应examId的四种类型题号列表
            Map<String, List<Integer>> map = new HashMap<>();
            map = examService.sortByTypeId(examId);
            //设置返回对象Vo
            RecordExamVo recordExamVo = new RecordExamVo();
            List<RecordAnsVo> radios = recordService.getRecordOptAns(rId, map.get("1"));
            List<RecordAnsVo> multiples = recordService.getRecordOptAns(rId, map.get("2"));
            List<RecordAnsVo> judges = recordService.getRecordOtherAns(rId, map.get("3"));
            List<RecordAnsVo> fillBlanks = recordService.getRecordOtherAns(rId, map.get("4"));
            recordExamVo.setRadios(radios);
            recordExamVo.setMultiples(multiples);
            recordExamVo.setJudges(judges);
            recordExamVo.setFillBlanks(fillBlanks);
            return R.success().put("data", recordExamVo);
        } catch (Exception e) {
            return R.error(CodeEnum.OTHER_ERROR);
        }

    }

    public R getExamRecord(GetRecordListParam param) {
        try {
            List<RecordExamListVo> voList = recordService.getExamRecord(param);
            return R.success().put("data", voList);
        } catch (Exception e) {
            return R.error(CodeEnum.OTHER_ERROR);
        }
    }
}
