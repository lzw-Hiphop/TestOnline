package com.testonline.service;

import com.testonline.bean.Testpaper;
import com.testonline.bean.TestpaperExample;
import com.testonline.testpaper.Answer;
import com.testonline.testpaper.TestPaperInfo;

import java.util.List;

public interface TestPaperService {

    void addTestPaper(TestPaperInfo testPaperInfo);

    void removeTestPaper(int paperId);

    void updateTestPaper(int paperId, TestPaperInfo testPaperInfo);

    List<Testpaper> selectTestPaper(TestpaperExample testpaperExample);

    List<Testpaper> listTestPaper();

    Testpaper selectTestPaperById(int paperId);

    List<Integer> checkAnswer(Answer answer, int paperId);

    int countTotalGrade(List<Integer> grades);

    void publishTestPaper(int paperId);

    void cancelTestPaper(int paperId);
}
