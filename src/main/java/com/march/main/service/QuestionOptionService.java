package com.march.main.service;

import com.march.main.entity.QuestionOption;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author March
 * @since 2020-03-24
 */
public interface QuestionOptionService extends IService<QuestionOption> {

    boolean addOpts(List<QuestionOption> optionList);

    boolean UpdateOpts(List<QuestionOption> optionList);
}
