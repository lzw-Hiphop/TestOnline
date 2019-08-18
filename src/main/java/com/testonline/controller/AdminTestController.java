package com.testonline.controller;

import com.testonline.bean.Subject;
import com.testonline.bean.Test;
import com.testonline.bean.Testpaper;
import com.testonline.controller.bean.AdminTestAnalyzeBean;
import com.testonline.controller.bean.AdminTestBean;
import com.testonline.controller.bean.AdminTestDetailBean;
import com.testonline.controller.bean.AdminTestPublishBean;
import com.testonline.service.SubjectService;
import com.testonline.service.TestPaperService;
import com.testonline.service.TestService;
import com.testonline.service.UserService;
import com.testonline.testpaper.TestPaperInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminTestController {
    @Resource
    SubjectService subjectService;
    @Resource
    TestPaperService testPaperService;
    @Resource
    TestService testService;
    @Resource
    UserService userService;

    @RequestMapping("/admin_test")
    public String adminTest(Model model) {
        List<Testpaper> testpapers = testPaperService.listTestPaper();
        List<Subject> subjects = subjectService.listSubject();
        Map<Integer, String> subjectMap = new HashMap<>();
        for (Subject subject : subjects) {
            subjectMap.put(subject.getSubjectid(), subject.getSubjectname());
        }
        List<AdminTestBean> adminTestBeans = new ArrayList<>();
        List<AdminTestPublishBean> adminTestPublishBeans = new ArrayList<>();
        for (Testpaper testpaper : testpapers) {
            if (testpaper.getPublished() == 0) {
                AdminTestBean bean = new AdminTestBean();
                bean.setPaperId(testpaper.getPaperid());
                bean.setPaperName(testpaper.getPapername());
                bean.setSubjectName(subjectMap.get(testpaper.getSubjectid()));
                adminTestBeans.add(bean);
            } else {
                AdminTestPublishBean bean = new AdminTestPublishBean();
                bean.setPaperId(testpaper.getPaperid());
                bean.setPaperName(testpaper.getPapername());
                bean.setSubjectName(subjectMap.get(testpaper.getSubjectid()));

                List<Test> tests = testService.selectByPaperId(bean.getPaperId());
                float average = 0;
                int userNum = 0;
                int sumGrade = 0;
                for (Test test : tests) {
                    if (test.getGrade() != null) {
                        userNum++;
                        sumGrade += test.getGrade();
                    }
                }
                if (userNum != 0) {
                    average = sumGrade / userNum;
                }

                bean.setUserNum(userNum);
                bean.setAverage(average);
                adminTestPublishBeans.add(bean);
            }

        }
        model.addAttribute("adminTestBeans", adminTestBeans);
        model.addAttribute("adminTestPublishBeans", adminTestPublishBeans);
        return "/admin_test";
    }

    @RequestMapping("/admin_test_detail")
    public String adminTestDetail(Model model, int paperId) {
        Testpaper testpaper = testPaperService.selectTestPaperById(paperId);
        int subjectId = testpaper.getSubjectid();
        String paperName = testpaper.getPapername();

        List<AdminTestDetailBean> adminTestDetailBeans = new ArrayList<>();
        TestPaperInfo testpaperInfo = new TestPaperInfo(testpaper);
        for (int i = 0; i < testpaperInfo.getQuestionNum(); i++) {
            AdminTestDetailBean bean = new AdminTestDetailBean();
            bean.setQuestion(testpaperInfo.getQuestions().get(i));
            bean.setChoices(testpaperInfo.getChoices().get(i));
            bean.setAnswer(testpaperInfo.getAnswers().get(i));
            bean.setGrade(testpaperInfo.getGrades().get(i));
            bean.setQuestionType(testpaperInfo.getQuestionTypes().get(i));
            adminTestDetailBeans.add(bean);
        }
        List<Subject> subjects = subjectService.listSubject();
        int questionNum = adminTestDetailBeans.size();

        model.addAttribute("paperId", paperId);
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("paperName", paperName);
        model.addAttribute("subjects", subjects);
        model.addAttribute("adminTestDetailBeans", adminTestDetailBeans);
        model.addAttribute("questionNum", questionNum);
        return "/admin_test_detail";
    }

    @RequestMapping("/admin_test_detail/update")
    public String adminTestDetailUpdate(HttpServletRequest request) {
        int paperId = Integer.parseInt(request.getParameter("paperId"));
        TestPaperInfo testPaperInfo = getTestPaperInfoFromRequest(request);
        testPaperService.updateTestPaper(paperId, testPaperInfo);
        return "redirect:/admin_test_detail?paperId=" + paperId;
    }

    @RequestMapping("/admin_test_add_setting")
    public String adminTestAddSetting() {
        return "/admin_test_add_setting";
    }

    @RequestMapping("/admin_test_add")
    public String adminTestAdd(Model model, int singleChoiceNum, int singleChoiceGrade, int singleChoiceAnswerNum, int multipleChoiceNum, int multipleChoiceGrade, int multipleChoiceAnswerNum) {
        List<Subject> subjects = subjectService.listSubject();
        model.addAttribute("singleChoiceNum", singleChoiceNum);
        model.addAttribute("singleChoiceGrade", singleChoiceGrade);
        model.addAttribute("singleChoiceAnswerNum", singleChoiceAnswerNum);
        model.addAttribute("multipleChoiceNum", multipleChoiceNum);
        model.addAttribute("multipleChoiceGrade", multipleChoiceGrade);
        model.addAttribute("multipleChoiceAnswerNum", multipleChoiceAnswerNum);
        model.addAttribute("subjects", subjects);
        return ("/admin_test_add");
    }

    @RequestMapping("/admin_test_add/execute")
    public String adminTestAddExecute(HttpServletRequest request) {
        TestPaperInfo testPaperInfo = getTestPaperInfoFromRequest(request);
        testPaperService.addTestPaper(testPaperInfo);
        return "redirect:/admin_test";
    }

    @RequestMapping("/admin_test/remove")
    public String adminTestRemove(int paperId) {
        testPaperService.removeTestPaper(paperId);
        return "redirect:/admin_test";
    }

    @RequestMapping("/admin_test/publish")
    public String adminTestPublish(int paperId) {
        testPaperService.publishTestPaper(paperId);
        return "redirect:/admin_test";
    }

    @RequestMapping("/admin_test/cancel")
    public String adminTestCancel(int paperId) {
        testPaperService.cancelTestPaper(paperId);
        return "redirect:/admin_test";
    }

    @RequestMapping("/admin_test_analyze")
    public String analyze(Model model, Integer paperId) {
        Testpaper testpaper = testPaperService.selectTestPaperById(paperId);
        Integer subjectId = testpaper.getSubjectid();
        Subject subject = subjectService.selectSubjectById(subjectId);
        String subjectName = subject.getSubjectname();
        model.addAttribute("testpaper", testpaper);
        model.addAttribute("subjectName", subjectName);
        List<Test> tests = testService.selectByPaperId(paperId);
        Integer low = 100;
        Integer max = 0;
        Integer sum = 0;
        Integer count = 0;
        List<AdminTestAnalyzeBean> adminTestAnalyzeBeans = new ArrayList<>();
        for (Test test : tests) {
            AdminTestAnalyzeBean bean = new AdminTestAnalyzeBean();
            if (test.getGrade() != null) {
                count++;
                if (test.getGrade() < low) {
                    low = test.getGrade();
                }
                if (test.getGrade() > max) {
                    max = test.getGrade();
                }
                sum += test.getGrade();
            }

            bean.setGrade(test.getGrade());
            bean.setTestId(test.getTestid());
            bean.setUserId(test.getUserid());
            bean.setRealName(userService.selectByUserId(test.getUserid()).getRealname());
            adminTestAnalyzeBeans.add(bean);
        }
        float average = 0;
        if (count != 0) {
            average = sum.floatValue() / count.floatValue();
        }

        model.addAttribute("low", low);
        model.addAttribute("max", max);
        model.addAttribute("average", average);
        model.addAttribute("count", count);
        model.addAttribute("adminTestAnalyzeBeans", adminTestAnalyzeBeans);
        return "/admin_test_analyze";

    }

    private TestPaperInfo getTestPaperInfoFromRequest(HttpServletRequest request) {
        String paperName = request.getParameter("paperName");
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        int questionNum = Integer.parseInt(request.getParameter("questionNum"));

        TestPaperInfo testPaperInfo = new TestPaperInfo(null);
        testPaperInfo.setPaperName(paperName);
        testPaperInfo.setPublished(0);
        testPaperInfo.setSubjectId(subjectId);

        for (int i = 1; i <= questionNum; i++) {
            String questionName = request.getParameter("question" + i);
            String[] choice = request.getParameterValues("choice" + i);
            String[] answer = request.getParameterValues("answer" + i);
            String questionTypeStr = request.getParameter("questionType" + i);
            int questionType;
            if (null == questionTypeStr) {
                questionType = 1;
            } else {
                questionType = Integer.parseInt(questionTypeStr);
            }
            String gradeStr = request.getParameter("grade" + i);
            int grade;
            if (null == gradeStr) {
                grade = 0;
            } else {
                grade = Integer.parseInt(gradeStr);
            }
            List<String> choiceList = new ArrayList<>();
            List<String> answerList = new ArrayList<>();
            for (String s : choice) {
                choiceList.add(s);
            }
            if (null == answer) {
                answerList.add("");
            } else for (String s : answer) {
                answerList.add(s);
            }
            testPaperInfo.addQuestion(questionName, choiceList, answerList, questionType, grade);
        }
        return testPaperInfo;
    }


}
