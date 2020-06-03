package com.march.main.biz;

import com.march.common.enums.ResultEnum;
import com.march.common.utils.JwtUtils;
import com.march.common.utils.MD5Utils;
import com.march.main.entity.User;
import com.march.common.enums.CodeEnum;
import com.march.main.params.GetUserListParam;
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
        if (user == null)
            return R.error(CodeEnum.USER_NOT_EXIST);
        else
            return R.success().put("data", user);
    }

    public R findUserList() {
        List<User> userList = userService.findAllUser();
        return R.success().put("data", userList);
    }

    public R findUserListById(GetUserListParam param) {
        System.out.println("param" + param);
        int rId = param.getRoleId();
        if (rId == 1 || rId == 2 || rId == 3) {
            List<User> userList = userService.findUserListById(param);
            return R.success().put("data", userList);
        }
        //返回权限错误
        return R.error(CodeEnum.AUTHORIZATION_FAILED);
    }

    public R getInfo(Integer id) {
        User user = userService.getInfo(id);
        return R.success().put("info", user);
    }

    public R login(LoginParam loginParam) {
        System.out.println(loginParam);
        //查出用户信息
        User user = userService.getByAccount(loginParam.getAccount());
        if (user != null) {
            String loginPwd = loginParam.getPassword();
            String dbPwd = user.getPassword();
            //验证账号密码
            if (user.getUserName().equals(loginParam.getAccount()) && MD5Utils.verify(loginPwd, MD5Utils.key, dbPwd)) {
                //登录成功，创建并返回token,对应用户的权限
                System.out.println(user);
                return R.success().put("msg", JwtUtils.creatJwt(user)).put("permission", user.getRoleId());
            }
            return R.error(CodeEnum.LOGIN_FAILED);
        }
        return R.error(CodeEnum.USER_NOT_EXIST);
    }

    public R register(User user) {
        //查出用户名是否已存在
        if (userService.getByAccount(user.getUserName()) != null)
            return R.error(CodeEnum.USER_ALREADY_EXIST);
        //输入的权限id不存在
        int rId = user.getRoleId();
        if (rId != 1 && rId != 2 && rId != 3)
            return R.error(CodeEnum.AUTHORIZATION_FAILED);
        String password = user.getPassword();
        String md5Str = MD5Utils.md5Encode(password, MD5Utils.key);
        user.setPassword(md5Str);
        //保存用户
        userService.save(user);
        return R.success("用户注册成功");
    }

    public R updateInfoById(User user) {
        //将更新的密码转MD5
        user.setPassword(MD5Utils.md5Encode(user.getPassword(), MD5Utils.key));
        if (userService.updateById(user)) {
            return R.success("用户信息更新成功");
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }

    public R batchDeleteUserByIds(Integer[] ids) {
        if (userService.batchDeleteUserByIds(ids) == ids.length)
            return R.success("人员批量删除成功");
        return R.error(CodeEnum.OTHER_ERROR);
    }
}
