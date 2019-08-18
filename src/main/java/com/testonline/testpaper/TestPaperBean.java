package com.testonline.testpaper;

import java.util.List;

public class TestPaperBean {

    private List<TestPaperQuestions> testPaperQuestions;

    public void setTestPaperQuestions(List<TestPaperQuestions> testPaperQuestions) {
        this.testPaperQuestions = testPaperQuestions;
    }

    public List<TestPaperQuestions> getTestPaperQuestions() {
        return testPaperQuestions;
    }

}