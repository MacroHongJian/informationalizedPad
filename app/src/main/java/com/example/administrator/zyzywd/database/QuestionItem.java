package com.example.administrator.zyzywd.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

public class QuestionItem extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknown")
    private String itemName;

    private String professions;

    private String risk;

    public String getItemName() { return itemName; }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getProfessions() { return professions; }

    public void setProfessions(String professions) { this.professions = professions; }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

}
