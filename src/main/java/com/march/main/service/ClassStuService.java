package com.march.main.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.march.main.entity.ClassStu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author March
 * @since 2020-04-12
 */
public interface ClassStuService extends IService<ClassStu> {

    ClassStu getOneByIds(int cId, Integer sId);
}
