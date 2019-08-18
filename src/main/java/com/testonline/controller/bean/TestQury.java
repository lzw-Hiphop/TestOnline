package com.testonline.controller.bean;

import com.testonline.bean.Test;

public class TestQury extends Test {

    private Integer paperId;
    private String subjectName;
    private String testTime;

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }


    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    private String testName;


    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        return "TestQury{" +
                "paperId=" + paperId +
                ", subjectName='" + subjectName + '\'' +
                ", testTime='" + testTime + '\'' +
                ", testName='" + testName + '\'' +
                '}';
    }
}
