package com.march.biz;

import com.march.entity.User;
import com.march.enums.CodeEnum;
import com.march.service.UserService;
import com.march.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBiz {

    @Autowired
    UserService userService;

    public R findUserById(Integer id) {
        User user = userService.findUserById(id);
        if(user==null)
            return R.error(CodeEnum.USER_NOT_EXIST);
        else
            return R.success().put("data",user);
    }
}
