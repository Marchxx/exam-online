package com.march.main.biz;

import com.march.common.enums.ResultEnum;
import com.march.common.utils.JwtUtils;
import com.march.main.entity.User;
import com.march.common.enums.CodeEnum;
import com.march.main.params.LoginParam;
import com.march.main.service.UserService;
import com.march.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBiz {

    @Autowired//必须加
    UserService userService;

    public R findUserById(Integer id) {
        User user = userService.findUserById(id);
        if(user==null)
            return R.error(CodeEnum.USER_NOT_EXIST);
        else
            return R.success().put("data",user);
    }

    public R findUserList() {
        List<User> userList = userService.findAllUser();
        return R.success().put("data",userList);
    }

    public R login(LoginParam loginParam) {
        //查出用户信息
        User user=userService.getByAccount(loginParam.getAccount());
        //验证账号密码
        if(user.getUserName().equals(loginParam.getAccount())&&user.getPassword().equals(loginParam.getPassword())){
            //登录成功，返回token
            return R.success().put("msg", JwtUtils.creatJwt(user));
        }
        else
            return R.error(CodeEnum.LOGIN_FAILED);
    }
}
