package com.testonline.bean;

import java.io.Serializable;
import java.util.Date;

public class Test implements Serializable {
    private Integer testid;

    private Integer paperid;

    private Integer userid;

    private Date testtime;

    private Integer grade;

    private String useranswer;

    private static final long serialVersionUID = 1L;

    public Integer getTestid() {
        return testid;
    }

    public void setTestid(Integer testid) {
        this.testid = testid;
    }

    public Integer getPaperid() {
        return paperid;
    }

    public void setPaperid(Integer paperid) {
        this.paperid = paperid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getTesttime() {
        return testtime;
    }

    public void setTesttime(Date testtime) {
        this.testtime = testtime;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer == null ? null : useranswer.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", testid=").append(testid);
        sb.append(", paperid=").append(paperid);
        sb.append(", userid=").append(userid);
        sb.append(", testtime=").append(testtime);
        sb.append(", grade=").append(grade);
        sb.append(", useranswer=").append(useranswer);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}