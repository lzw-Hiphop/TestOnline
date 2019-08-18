package com.testonline.controller;

import com.testonline.bean.Test;
import com.testonline.bean.Testpaper;
import com.testonline.controller.bean.TestingBean;
import com.testonline.service.SubjectService;
import com.testonline.service.TestPaperService;
import com.testonline.service.TestService;
import com.testonline.testpaper.Answer;
import com.testonline.testpaper.TestPaperInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TestingController {
    @Resource
    TestPaperService testPaperService;
    @Resource
    SubjectService subjectService;
    @Resource
    TestService testService;

    @RequestMapping("/testing")
    public String testing(Model model, int paperId) {
        Testpaper testpaper = testPaperService.selectTestPaperById(paperId);
        TestPaperInfo testPaperInfo = new TestPaperInfo(testpaper);
        String subjectName = subjectService.selectSubjectById(testPaperInfo.getSubjectId()).getSubjectname();
        String paperName = testPaperInfo.getPaperName();
        int questionNum = testPaperInfo.getQuestionNum();
        List<TestingBean> testingBeans = new ArrayList<>();
        for (int i = 0; i < questionNum; i++) {
            TestingBean bean = new TestingBean();
            bean.setQuestion(testPaperInfo.getQuestions().get(i));
            bean.setChoices(testPaperInfo.getChoices().get(i));
            bean.setAnswer(testPaperInfo.getAnswers().get(i));
            bean.setQuestionType(testPaperInfo.getQuestionTypes().get(i));
            bean.setGrade(testPaperInfo.getGrades().get(i));
            testingBeans.add(bean);
        }

        model.addAttribute("paperId", paperId);
        model.addAttribute("subjectName", subjectName);
        model.addAttribute("paperName", paperName);
        model.addAttribute("testingBeans", testingBeans);
        model.addAttribute("questionNum", questionNum);
        return "/testing";
    }

    @RequestMapping("/testing/submit")
    public String testingSubmit(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        int userId = (int) request.getSession().getAttribute("userId");
        int paperId = Integer.parseInt(request.getParameter("paperId"));
        int questionNum = Integer.parseInt(request.getParameter("questionNum"));
        Answer answer = new Answer();
        for (int i = 1; i <= questionNum; i++) {
            String[] answers = request.getParameterValues("answer" + i);
            List<String> answerList = new ArrayList<>();
            if (null != answers) {
                for (String s : answers) {
                    answerList.add(s);
                }
            } else {
                answerList.add("");
            }
            answer.addAnswer(answerList);
        }
        List<Integer> grades = testPaperService.checkAnswer(answer, paperId);
        int grade = testPaperService.countTotalGrade(grades);
        Test test = new Test();
        test.setUserid(userId);
        test.setGrade(grade);
        test.setPaperid(paperId);
        test.setTesttime(new Date());
        test.setUseranswer(answer.answerListToJson());
        testService.add(test);
        return "redirect:/test_review?paperId=" + paperId;
    }

}
