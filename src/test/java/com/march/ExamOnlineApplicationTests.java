package com.march;

import com.march.common.enums.CodeEnum;
import com.march.common.utils.JwtUtils;
import com.march.common.utils.R;
import com.march.main.dao.QuestionMapper;
import com.march.main.entity.User;
import com.march.main.service.UserService;
import com.march.main.vo.QuestionOptionVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ExamOnlineApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    QuestionMapper questionMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testJwt() {
        User user = userService.getByAccount("admin");
        System.out.println(user);
        String jwt = JwtUtils.creatJwt(user);
        System.out.println(jwt);
        System.out.println(JwtUtils.checkJWT(jwt));
    }

    @Test
    void testGetQuestionlist() {
        List<QuestionOptionVo> voList = questionMapper.getOpsListByTypeId(1);
        System.out.println(voList);
    }
}
