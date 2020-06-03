package com.march;

import com.march.common.utils.JwtUtils;
import com.march.main.dao.ClassMapper;
import com.march.main.dao.QuestionMapper;
import com.march.main.entity.ExamQuestion;
import com.march.main.entity.User;
import com.march.main.params.GetQuestListParam;
import com.march.main.service.*;
import com.march.main.vo.QuestionOptionVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class ExamOnlineApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionAnswerService qAnsService;
    @Autowired
    ClassMapper classMapper;
    @Autowired
    ClassService classService;
    @Autowired
    ExamService examService;
    @Autowired
    ExamQuestionService examQuestionService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreatJwt() {
        User user = userService.getByAccount("5120164179");
        System.out.println(user);
        String jwt = JwtUtils.creatJwt(user);
        System.out.println(jwt);
        System.out.println(JwtUtils.checkJWT(jwt));

        User user1 = userService.getByAccount("5120164179");
        System.out.println(user1);
        String jwt1 = JwtUtils.creatJwt(user);
        System.out.println(jwt1);
        System.out.println(JwtUtils.checkJWT(jwt1));
    }

    @Test
    void testCheckJwt() {

    }

    @Test
    void testGetQuestionlist() {
        GetQuestListParam param = new GetQuestListParam();
        //param.setCategoryId(0);
        param.setTypeId(1);
        List<QuestionOptionVo> voList = questionMapper.getOpsListById(param);
        System.out.println(voList);
    }

    @Test
    void testGetQuestionlist2() {
        //测试返回班级学生列表
//        List<ClassStuVo> vos = classMapper.getStuListById(1);
//        System.out.println(vos);
        //测试增加学生
//        Integer[] stus = {1,2};
//        int addStu = classService.getAddStu(1, stus);
//        System.out.println(addStu);
//        //测试删除学生
        Integer[] stus = {1,2};
        int addStu = classService.getDelStu(1, stus);
        System.out.println(addStu);
    }

    @Test
    void testInsertExam(){
        //测试MybatisPlus获取insert自动增量
        ExamQuestion question=new ExamQuestion();
        question.setId(15);
        question.setExamId(1);
        System.out.println(question.getId());
        boolean insert = question.insert();
        System.out.println(question.getId());
    }

    @Test
    void testExcel(){

    }

    @Test
    void testExamMap(){
        Map<String, List<Integer>> map = examService.sortByTypeId(1);
        List<Integer> list = map.get("1");
        System.out.println(list);
    }
}