package com.example.administrator.zyzywd.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class AnswerHistory extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknown")
    private String answerHistoryId;

    private String plane;

    private String appraiser;

    private String persion;

    private String[] questionContent;

    private String[] answer;

    public void setAnswerHistoryId(String answerHistoryId) { this.answerHistoryId = answerHistoryId;}
    public String getAnswerHistoryId() { return answerHistoryId; }

    public void setPlane(String plane) { this.plane = plane; }
    public String getPlane() { return plane; }

    public void setAppraiser(String appraiser) { this.appraiser = appraiser; }
    public String getAppraiser() { return appraiser; }

    public void setPersion(String persion) { this.persion = persion; }
    public String getPersion() { return persion; }

    public void setQuestionContent(String questionContent, int i) { this.questionContent[i] = questionContent; }
    public String getQuestionContent(int i) { return questionContent[i]; }

    public void setAnswer(String answer, int i) { this.answer[i] = answer; }
    public String getAnswer(int i) { return answer[i]; }

}
