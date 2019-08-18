package com.testonline.controller;


import com.testonline.bean.Subject;
import com.testonline.bean.Test;
import com.testonline.bean.Testpaper;
import com.testonline.controller.bean.TestBean;
import com.testonline.controller.bean.TestQury;
import com.testonline.controller.bean.TestReviewBean;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @Resource
    private TestService testService;
    @Resource
    SubjectService subjectService;
    @Resource
    TestPaperService testPaperService;

    @RequestMapping("/test_info")
    public String testInfo(Model model, HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");

        //查询未开始的考试
        List<Testpaper> testpapers = testService.listUncompletedPaper(userId);
        List<TestBean> testBeans = new ArrayList<>();
        for (Testpaper testpaper : testpapers) {
            TestBean bean = new TestBean();
            String subjectName = subjectService.selectSubjectById(testpaper.getSubjectid()).getSubjectname();
            bean.setPaperId(testpaper.getPaperid());
            bean.setPaperName(testpaper.getPapername());
            bean.setSubjectName(subjectName);
            testBeans.add(bean);
        }
        model.addAttribute("testBeans", testBeans);
        return "/test_info";
    }

    @RequestMapping("/test_record")     //查询已完成的考试
    public String testRecord(Model model, HttpServletRequest request) {

        int userId = (int) request.getSession().getAttribute("userId");

        List<Test> tests = testService.selectByUserId(userId);
        ArrayList<TestQury> completedTests = new ArrayList<TestQury>();
        for (Test test : tests) {
            if (test.getGrade() != null) {
                Integer tid = test.getTestid();
                Integer paperid = test.getPaperid();
                Testpaper testpaper = testPaperService.selectTestPaperById(paperid);
                String tname = testpaper.getPapername();
                Integer sid = testpaper.getSubjectid();
                Subject subject = subjectService.selectSubjectById(sid);
                String sname = subject.getSubjectname();
                TestQury testQury = new TestQury();
                testQury.setPaperId(test.getPaperid());
                testQury.setTestid(test.getTestid());
                testQury.setTestName(tname);
                testQury.setGrade(test.getGrade());
                testQury.setSubjectName(sname);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(test.getTesttime());
                testQury.setTestTime(dateString);
                completedTests.add(testQury);
                //System.out.println(testQury);
            }
        }
        model.addAttribute("completedTests", completedTests);
        return "/test_record";
    }

    @RequestMapping("/test_review")
    public String testReview(Model model, int paperId, HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");
        Test test = testService.selectByPaperIdAndUserId(paperId, userId);
        Testpaper testpaper = testPaperService.selectTestPaperById(paperId);
        String subjectName = subjectService.selectSubjectById(testpaper.getSubjectid()).getSubjectname();
        String paperName = testpaper.getPapername();
        int grade = test.getGrade();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String testTime = formatter.format(test.getTesttime());

        TestPaperInfo testPaperInfo = new TestPaperInfo(testpaper);
        Answer answer = new Answer();
        answer.setAnswerListFromJson(test.getUseranswer());
        List<TestReviewBean> testReviewBeans = new ArrayList<>();
        List<Integer> userGrades = testPaperService.checkAnswer(answer, paperId);

        for (int i = 0; i < testPaperInfo.getQuestionNum(); i++) {
            TestReviewBean bean = new TestReviewBean();
            bean.setQuestion(testPaperInfo.getQuestions().get(i));
            bean.setChoices(testPaperInfo.getChoices().get(i));
            bean.setGrade(testPaperInfo.getGrades().get(i));
            bean.setPaperAnswer(testPaperInfo.getAnswers().get(i));
            bean.setUserAnswer(answer.getAnswerList().get(i));
            bean.setUserGrade(userGrades.get(i));
            testReviewBeans.add(bean);
        }

        model.addAttribute("paperId", paperId);
        model.addAttribute("subjectName", subjectName);
        model.addAttribute("testTime", testTime);
        model.addAttribute("paperName", paperName);
        model.addAttribute("grade", grade);
        model.addAttribute("testReviewBeans", testReviewBeans);
        return "/test_review";
    }

}
