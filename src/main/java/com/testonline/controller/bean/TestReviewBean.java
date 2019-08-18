package com.testonline.controller.bean;

import java.util.List;

public class TestReviewBean {
    private String question;
    private List<String> choices;
    private List<String> userAnswer;
    private List<String> paperAnswer;
    private int userGrade;
    private int grade;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public List<String> getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(List<String> userAnswer) {
        this.userAnswer = userAnswer;
    }

    public List<String> getPaperAnswer() {
        return paperAnswer;
    }

    public void setPaperAnswer(List<String> paperAnswer) {
        this.paperAnswer = paperAnswer;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(int userGrade) {
        this.userGrade = userGrade;
    }
}
