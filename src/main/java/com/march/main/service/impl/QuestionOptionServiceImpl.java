package com.march.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.march.main.entity.Question;
import com.march.main.entity.QuestionOption;
import com.march.main.dao.QuestionOptionMapper;
import com.march.main.service.QuestionOptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class QuestionOptionServiceImpl extends ServiceImpl<QuestionOptionMapper, QuestionOption> implements QuestionOptionService {

    @Override
    public boolean addOrUpdateOpts(List<QuestionOption> optionList) {
        for (QuestionOption option : optionList) {
            //QueryWrapper必须加上实体类
            //每次取出list中的option，匹配联合主键的条件，更新数据库对象
            QueryWrapper<QuestionOption> wrapper = new QueryWrapper<>();
            wrapper.eq("question_id", option.getQuestionId()).eq("idx", option.getIdx());
            boolean flag = option.update(wrapper);
            if (!flag)
                return false;
        }
        return true;
    }
}
