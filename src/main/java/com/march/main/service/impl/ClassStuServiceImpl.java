package com.march.main.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.march.main.dao.ClassStuMapper;
import com.march.main.entity.ClassStu;
import com.march.main.service.ClassStuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author March
 * @since 2020-04-12
 */
@Service
public class ClassStuServiceImpl extends ServiceImpl<ClassStuMapper, ClassStu> implements ClassStuService {

    @Override
    public ClassStu getOneByIds(int cId, Integer sId) {
        ClassStu stu = new ClassStu();
        return stu.selectOne(new QueryWrapper<ClassStu>().eq("class_id", cId)
                .eq("stu_id", sId));
    }
}
