package com.example.administrator.zyzywd.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class WDXM extends LitePalSupport {

    @Column(unique = true, defaultValue = "unknown")
    private String WDXMBS;
    private char FXXS;
    private String XMMC;
    private String XMJX;
    private String XMZY;
    private int XMPX;
    private String XMTB;
    private int WDCS;
    private String SJC;
    private String JSZB;
    private String P_PIC;
    private String P_ADDRESS;

    public String getP_ADDRESS() {
        return P_ADDRESS;
    }

    public void setP_ADDRESS(String p_ADDRESS) {
        P_ADDRESS = p_ADDRESS;
    }

    public String getWDXMBS() {
        return WDXMBS;
    }

    public void setWDXMBS(String WDXMBS) {
        this.WDXMBS = WDXMBS;
    }

    public char getFXXS() {
        return FXXS;
    }

    public void setFXXS(char FXXS) {
        this.FXXS = FXXS;
    }

    public String getXMMC() {
        return XMMC;
    }

    public void setXMMC(String XMMC) {
        this.XMMC = XMMC;
    }

    public String getXMJX() {
        return XMJX;
    }

    public void setXMJX(String XMJX) {
        this.XMJX = XMJX;
    }

    public String getXMZY() {
        return XMZY;
    }

    public void setXMZY(String XMZY) {
        this.XMZY = XMZY;
    }

    public int getXMPX() {
        return XMPX;
    }

    public void setXMPX(int XMPX) {
        this.XMPX = XMPX;
    }

    public String getXMTB() {
        return XMTB;
    }

    public void setXMTB(String XMTB) {
        this.XMTB = XMTB;
    }

    public int getWDCS() {
        return WDCS;
    }

    public void setWDCS(int WDCS) {
        this.WDCS = WDCS;
    }

    public String getSJC() {
        return SJC;
    }

    public void setSJC(String SJC) {
        this.SJC = SJC;
    }

    public String getJSZB() {
        return JSZB;
    }

    public void setJSZB(String JSZB) {
        this.JSZB = JSZB;
    }

    public String getP_PIC() {
        return P_PIC;
    }

    public void setP_PIC(String p_PIC) {
        P_PIC = p_PIC;
    }
}
