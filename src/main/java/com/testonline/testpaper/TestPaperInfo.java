package com.testonline.testpaper;

import com.google.gson.Gson;
import com.testonline.bean.Testpaper;

import java.util.ArrayList;
import java.util.List;

public class TestPaperInfo {
    private String paperName = null;
    private int subjectId = 0;
    private List<String> questions = new ArrayList<>();
    private List<List<String>> choices = new ArrayList<>();
    private List<List<String>> answers = new ArrayList<>();
    private List<Integer> questionTypes = new ArrayList<>();
    private List<Integer> grades = new ArrayList<>();
    private int published = 0;

    public TestPaperInfo(Testpaper testpaper) {
        if (null != testpaper) {
            paperName = testpaper.getPapername();
            subjectId = testpaper.getSubjectid();
            published = testpaper.getPublished();
            setQuestionsFromJson(testpaper.getQuestion());
        }
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public void addQuestion(String question, List<String> choice, List<String> answer, int questionType, int grade) {
        questions.add(question);
        choices.add(choice);
        answers.add(answer);
        questionTypes.add(questionType);
        grades.add(grade);
    }

    public void removeQuestion(int id) {
        questions.remove(id);
        choices.remove(id);
        answers.remove(id);
        questionTypes.remove(id);
        grades.remove(id);
    }

    public void updateQuestion(int id, String question, List<String> choice, List<String> answer, int questionType, int grade) {
        questions.set(id, question);
        choices.set(id, choice);
        answers.set(id, answer);
        questionTypes.set(id, questionType);
        grades.set(id, grade);
    }

    public List<String> getQuestions() {
        return questions;
    }

    public List<List<String>> getChoices() {
        return choices;
    }

    public List<List<String>> getAnswers() {
        return answers;
    }

    public List<Integer> getQuestionTypes() {
        return questionTypes;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public int getQuestionNum() {
        return questions.size();
    }

    public void setQuestionsFromJson(String jsonStr) {
        TestPaperBean testPaperBean = new Gson().fromJson(jsonStr, TestPaperBean.class);
        List<TestPaperQuestions> testPaperQuestions = testPaperBean.getTestPaperQuestions();
        questions.clear();
        choices.clear();
        answers.clear();
        questionTypes.clear();
        grades.clear();
        for (TestPaperQuestions testPaperQuestion : testPaperQuestions) {
            questions.add(testPaperQuestion.getQuestion());
            choices.add(testPaperQuestion.getChoices());
            answers.add(testPaperQuestion.getAnswer());
            questionTypes.add(testPaperQuestion.getQuestionType());
            grades.add(testPaperQuestion.getGrade());
        }
    }

    public String questionsToJson() {
        TestPaperBean testPaperBean = new TestPaperBean();
        List<TestPaperQuestions> testPaperQuestions = new ArrayList<>();
        for (int i = 0; i < getQuestionNum(); i++) {
            TestPaperQuestions testPaperQuestion = new TestPaperQuestions();
            testPaperQuestion.setQuestion(questions.get(i));
            testPaperQuestion.setChoices(choices.get(i));
            testPaperQuestion.setAnswer(answers.get(i));
            testPaperQuestion.setQuestionType(questionTypes.get(i));
            testPaperQuestion.setGrade(grades.get(i));
            testPaperQuestions.add(testPaperQuestion);
        }
        testPaperBean.setTestPaperQuestions(testPaperQuestions);
        return new Gson().toJson(testPaperBean);
    }
}
