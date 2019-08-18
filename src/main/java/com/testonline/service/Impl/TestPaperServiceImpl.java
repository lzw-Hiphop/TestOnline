package com.testonline.service.Impl;

import com.testonline.bean.Testpaper;
import com.testonline.bean.TestpaperExample;
import com.testonline.mapper.TestMapper;
import com.testonline.mapper.TestpaperMapper;
import com.testonline.service.TestPaperService;
import com.testonline.service.TestService;
import com.testonline.testpaper.Answer;
import com.testonline.testpaper.TestPaperInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestPaperServiceImpl implements TestPaperService {
    @Resource
    TestpaperMapper testpaperMapper;
    @Resource
    TestMapper testMapper;
    @Resource
    TestService testService;

    @Override
    public void addTestPaper(TestPaperInfo testPaperInfo) {

      /*  TestpaperExample testpaperExample = new TestpaperExample();
        testpaperExample.setOrderByClause("paperid DESC");
        int paperId;
        if (testpaperMapper.selectByExample(testpaperExample).size() == 0) {
            paperId = 1;
        } else {
            paperId = testpaperMapper.selectByExample(testpaperExample).get(0).getPaperid() + 1;
        }*/


        Testpaper testpaper = new Testpaper();
        //testpaper.setPaperid(paperId);
        testpaper.setPapername(testPaperInfo.getPaperName());
        testpaper.setQuestion(testPaperInfo.questionsToJson());
        testpaper.setSubjectid(testPaperInfo.getSubjectId());
        testpaper.setPublished(testPaperInfo.getPublished());
        testpaperMapper.insert(testpaper);
    }

    @Override
    public void removeTestPaper(int paperId) {
        testpaperMapper.deleteByPrimaryKey(paperId);
        testService.delebeByPaperId(paperId);
    }

    @Override
    public void updateTestPaper(int paperId, TestPaperInfo testPaperInfo) {
        Testpaper testpaper = testpaperMapper.selectByPrimaryKey(paperId);
        testpaper.setPapername(testPaperInfo.getPaperName());
        testpaper.setQuestion(testPaperInfo.questionsToJson());
        testpaper.setSubjectid(testPaperInfo.getSubjectId());
        testpaper.setPublished(testPaperInfo.getPublished());
        testpaperMapper.updateByPrimaryKeySelective(testpaper);
    }

    @Override
    public Testpaper selectTestPaperById(int paperId) {
        return testpaperMapper.selectByPrimaryKey(paperId);
    }

    @Override
    public List<Testpaper> selectTestPaper(TestpaperExample testpaperExample) {
        return testpaperMapper.selectByExample(testpaperExample);
    }

    @Override
    public List<Integer> checkAnswer(Answer userAnswer, int paperId) {
        Testpaper testpaper = selectTestPaperById(paperId);
        TestPaperInfo testPaperInfo = new TestPaperInfo(testpaper);
        List<List<String>> userAnswerList = userAnswer.getAnswerList();
        List<List<String>> paperAnswerList = testPaperInfo.getAnswers();
        List<Integer> grades = testPaperInfo.getGrades();
        List<Integer> resultGrades = new ArrayList<>();
        Boolean bool = false;
        for (int i = 0; i < testPaperInfo.getQuestionNum(); i++) {
            for (int j = 0; j < paperAnswerList.get(i).size(); j++) {
                if (paperAnswerList.get(i).size() != userAnswerList.get(i).size()) {
                    bool = false;
                    break;
                }
                if (!paperAnswerList.get(i).get(j).equals(userAnswerList.get(i).get(j))) {
                    bool = false;
                    break;
                }
                bool = true;
            }
            if (bool) {
                resultGrades.add(grades.get(i));
            } else {
                resultGrades.add(0);
            }

        }

        return resultGrades;
    }

    @Override
    public int countTotalGrade(List<Integer> grades) {
        int sum = 0;
        for (int i : grades) {
            sum = sum + i;
        }
        return sum;
    }

    @Override
    public List<Testpaper> listTestPaper() {
        TestpaperExample testpaperExample = new TestpaperExample();
        return testpaperMapper.selectByExample(testpaperExample);
    }

    @Override
    public void publishTestPaper(int paperId) {
        Testpaper testpaper = testpaperMapper.selectByPrimaryKey(paperId);
        testpaper.setPublished(1);
        testpaperMapper.updateByPrimaryKeySelective(testpaper);
    }

    @Override
    public void cancelTestPaper(int paperId) {
        Testpaper testpaper = testpaperMapper.selectByPrimaryKey(paperId);
        testpaper.setPublished(0);
        testpaperMapper.updateByPrimaryKeySelective(testpaper);
    }
}
