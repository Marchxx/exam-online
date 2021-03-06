package com.march.main.service;

import com.march.common.utils.R;
import com.march.main.entity.QuestionCategory;
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
public interface QuestionCategoryService extends IService<QuestionCategory> {

    List<QuestionCategory> getAllCategory();

    boolean addOrUpdateQcategory(QuestionCategory questionCategory);

    int delQcategoryByIds(Integer[] ids);
}
