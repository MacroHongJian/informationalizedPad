package com.example.administrator.zyzywd.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class QuestionContent extends LitePalSupport {

    @Column(unique = true, defaultValue = "unknown")
    private String contentName;

    private String itemName;

    private String types;

    private String answer;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getItemId() {
        return itemName;
    }

    public void setItemId(String itemId) {
        this.itemName = itemId;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

}
