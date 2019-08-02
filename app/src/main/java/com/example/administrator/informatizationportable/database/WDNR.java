package com.example.administrator.zyzywd.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class WDNR extends LitePalSupport {

    @Column(unique = true, defaultValue = "unknown")
    private String WDNRBS;
    private String WDXMBS;
    private String WDLX;
    private String WDNR;
    private String WDDA;
    private String WDFL;
    private String SYFW;
    private String XXA;
    private String XXB;
    private String XXC;
    private String XXD;
    private String XXE;
    private String XXF;
    private String XXG;
    private String XXH;
    private String XXS;
    private String SJC;
    private String WDDA_TEXT;

    public String getWDNRBS() {
        return WDNRBS;
    }

    public void setWDNRBS(String WDNRBS) {
        this.WDNRBS = WDNRBS;
    }

    public String getWDXMBS() {
        return WDXMBS;
    }

    public void setWDXMBS(String WDXMBS) {
        this.WDXMBS = WDXMBS;
    }

    public String getWDLX() {
        return WDLX;
    }

    public void setWDLX(String WDLX) {
        this.WDLX = WDLX;
    }

    public String getWDNR() {
        return WDNR;
    }

    public void setWDNR(String WDNR) {
        this.WDNR = WDNR;
    }

    public String getWDDA() {
        return WDDA;
    }

    public void setWDDA(String WDDA) {
        this.WDDA = WDDA;
    }

    public String getWDFL() {
        return WDFL;
    }

    public void setWDFL(String WDFL) {
        this.WDFL = WDFL;
    }

    public String getSYFW() {
        return SYFW;
    }

    public void setSYFW(String SYFW) {
        this.SYFW = SYFW;
    }

    public String getXXA() {
        return XXA;
    }

    public void setXXA(String XXA) {
        this.XXA = XXA;
    }

    public String getXXB() {
        return XXB;
    }

    public void setXXB(String XXB) {
        this.XXB = XXB;
    }

    public String getXXC() {
        return XXC;
    }

    public void setXXC(String XXC) {
        this.XXC = XXC;
    }

    public String getXXD() {
        return XXD;
    }

    public void setXXD(String XXD) {
        this.XXD = XXD;
    }

    public String getXXE() {
        return XXE;
    }

    public void setXXE(String XXE) {
        this.XXE = XXE;
    }

    public String getXXF() {
        return XXF;
    }

    public void setXXF(String XXF) {
        this.XXF = XXF;
    }

    public String getXXG() {
        return XXG;
    }

    public void setXXG(String XXG) {
        this.XXG = XXG;
    }

    public String getXXH() {
        return XXH;
    }

    public void setXXH(String XXH) {
        this.XXH = XXH;
    }

    public String getXXS() {
        return XXS;
    }

    public void setXXS(String XXS) {
        this.XXS = XXS;
    }

    public String getSJC() {
        return SJC;
    }

    public void setSJC(String SJC) {
        this.SJC = SJC;
    }

    public String getWDDA_TEXT() {
        return WDDA_TEXT;
    }

    public void setWDDA_TEXT(String WDDA_TEXT) {
        this.WDDA_TEXT = WDDA_TEXT;
    }
}
