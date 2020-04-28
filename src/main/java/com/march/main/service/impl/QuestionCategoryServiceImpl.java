package com.march.main.service.impl;

import com.march.main.entity.QuestionCategory;
import com.march.main.dao.QuestionCategoryMapper;
import com.march.main.service.QuestionCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class QuestionCategoryServiceImpl extends ServiceImpl<QuestionCategoryMapper, QuestionCategory> implements QuestionCategoryService {

    @Autowired
    QuestionCategoryMapper questionCategoryMapper;

    @Override
    public List<QuestionCategory> getAllCategory() {
        QuestionCategory qc = new QuestionCategory();
        return qc.selectAll();
    }

    @Override
    public boolean addOrUpdateQcategory(QuestionCategory questionCategory) {
        return questionCategory.insertOrUpdate();
    }

    @Override
    public int delQcategoryByIds(Integer[] ids) {
        List<Integer> list = new ArrayList<>();
        for (Integer id : ids) {
            list.add(id);
        }
        return questionCategoryMapper.deleteBatchIds(list);
    }
}
