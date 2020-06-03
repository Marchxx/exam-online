package com.march.main.biz;

import com.march.common.office.pojo.QuestionExcel;
import com.march.common.utils.R;
import com.march.main.entity.Question;
import com.march.main.entity.QuestionOption;
import com.march.main.params.QuestionOptParam;
import com.march.main.params.QuestionOtherParam;
import com.march.main.service.QuestionService;
import io.swagger.models.auth.In;
import net.sf.json.JSONObject;
import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImportBiz {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionBiz questionBiz;

    //分别处理四种题型的导入
    public void importOpts(QuestionExcel q) {
        try {
            //构造question对象与List<QuestionOption>：QuestionOptParam对象
            QuestionOptParam optParam = new QuestionOptParam();
            //1.首先构造question对象
            Question question = new Question();
            question.setQuestionName(q.getQuestionName());
            question.setQuestionScore(q.getQuestionScore());
            question.setQuestionTypeId(q.getQuestionTypeId());
            //根据科目类别名称获取类别id
            Integer categoryId = questionService.getCategoryIdByName(q.getQuestionCategoryName());
            question.setQuestionCategoryId(categoryId);
            optParam.setQuestion(question);
            //2.构造List<QuestionOption>
            List<QuestionOption> optionList = handleOptList(q);
            optParam.setOptionList(optionList);

            //System.out.println(optParam);
            //执行导入方法
            questionBiz.addOrUpdateOpts(optParam);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void importOthers(QuestionExcel q) {
        try {
            //otherParam.getQuestion().setQuestionName("sd");与ExamBiz写法一样，但会报错
            //构造question对象与answer：QuestionOtherParam对象
            QuestionOtherParam otherParam = new QuestionOtherParam();
            //1.首先构造question对象
            Question question = new Question();
            question.setQuestionName(q.getQuestionName());
            question.setQuestionScore(q.getQuestionScore());
            question.setQuestionTypeId(q.getQuestionTypeId());
            //根据科目类别名称获取类别id
            Integer categoryId = questionService.getCategoryIdByName(q.getQuestionCategoryName());
            question.setQuestionCategoryId(categoryId);
            otherParam.setQuestion(question);
            //2.构造answer
            otherParam.setAnswer(q.getAnswer());
            //System.out.println(otherParam);
            //执行导入方法
            questionBiz.addOrUpdateOther(otherParam);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public List<QuestionOption> handleOptList(QuestionExcel q) {
        List<QuestionOption> optionList = new ArrayList<>();
        //1.构造四个QuestionOption对象
        String optAnswer = q.getAnswer();

        QuestionOption option1 = new QuestionOption();
        option1.setIdx(1);
        option1.setContent(q.getA());
        if (optAnswer.contains("A")) option1.setAnswer(1);
        else option1.setAnswer(0);
        optionList.add(option1);

        QuestionOption option2 = new QuestionOption();
        option2.setIdx(2);
        option2.setContent(q.getB());
        if (optAnswer.contains("B")) option2.setAnswer(1);
        else option2.setAnswer(0);
        optionList.add(option2);

        QuestionOption option3 = new QuestionOption();
        option3.setIdx(3);
        option3.setContent(q.getC());
        if (optAnswer.contains("C")) option3.setAnswer(1);
        else option3.setAnswer(0);
        optionList.add(option3);

        QuestionOption option4 = new QuestionOption();
        option4.setIdx(4);
        option4.setContent(q.getD());
        if (optAnswer.contains("D")) option4.setAnswer(1);
        else option4.setAnswer(0);
        optionList.add(option4);
        return optionList;
    }

}
