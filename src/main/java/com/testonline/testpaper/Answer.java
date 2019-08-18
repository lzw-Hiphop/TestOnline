package com.testonline.testpaper;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Answer {
    private List<List<String>> answerList = new ArrayList<>();

    public List<List<String>> getAnswerList() {
        return answerList;
    }

    public void addAnswer(List<String> answer) {
        answerList.add(answer);
    }

    public void removeAnswer(int id) {
        answerList.remove(id);
    }

    public void updateAnswer(int id, List<String> answer) {
        answerList.set(id, answer);
    }

    public void setAnswerListFromJson(String jsonStr) {
        AnswerBean answerBean = new Gson().fromJson(jsonStr, AnswerBean.class);
        answerList = answerBean.getAnswer();
    }

    public String answerListToJson() {
        AnswerBean answerBean = new AnswerBean();
        answerBean.setAnswer(answerList);
        return new Gson().toJson(answerBean);
    }


}
