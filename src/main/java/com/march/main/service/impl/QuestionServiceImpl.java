package com.march.main.service.impl;

import com.march.main.entity.Question;
import com.march.main.dao.QuestionMapper;
import com.march.main.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}
