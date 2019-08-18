package com.testonline.testpaper;

import java.util.List;

public class TestPaperQuestions {

    private String question;
    private int questionType;
    private List<String> choices;
    private List<String> answer;
    private int grade;

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}