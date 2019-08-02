package com.example.administrator.zyzywd.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class WDJL extends LitePalSupport {

    @Column(unique = true, defaultValue = "unknown")
    private String WDJLBS;
    private String WDXMBS;
    private Date WDSJ;
    private String CZZBS;
    private String JLLX;
    private int KGTSL;
    private int DDKGT;
    private String ZGTPJ;
    private String KGTPJ;
    private String ZHPJ;
    private String SJC;
    private int ZGTSL;
    private String ZZZ;
    private String FJH;

    public void setWDJLBS(String WDJLBS) {
        this.WDJLBS = WDJLBS;
    }

    public String getWDJLBS() {
        return WDJLBS;
    }

    public String getCZZBS() {
        return CZZBS;
    }

    public void setCZZBS(String CZZBS) {
        this.CZZBS = CZZBS;
    }

    public String getZZZ() {
        return ZZZ;
    }

    public void setZZZ(String ZZZ) {
        this.ZZZ = ZZZ;
    }

    public String getFJH() {
        return FJH;
    }

    public void setFJH(String FJH) {
        this.FJH = FJH;
    }

    public void setWDXMBS(String WDXMBS) {
        this.WDXMBS = WDXMBS;
    }

    public String getWDXMBS() {
        return WDXMBS;
    }

    public String getKGTPJ() {
        return KGTPJ;
    }

    public void setKGTPJ(String KGTPJ) {
        this.KGTPJ = KGTPJ;
    }

    public String getZGTPJ() {
        return ZGTPJ;
    }

    public void setZGTPJ(String ZGTPJ) {
        this.ZGTPJ = ZGTPJ;
    }

    public void setWDSJ(Date WDSJ) {
        this.WDSJ = WDSJ;
    }

    public Date getWDSJ() {
        return WDSJ;
    }

    public void setZHPJ(String ZHPJ) {
        this.ZHPJ = ZHPJ;
    }

    public String getZHPJ() {
        return ZHPJ;
    }
}
