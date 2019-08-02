package com.example.administrator.zyzywd.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class DLQX extends LitePalSupport {
    private String GWMC;
    @Column(unique = true, defaultValue = "unknown")
    private String RID;
    private String YHZY;
    private String ZSXM;
    private String DXGZWD;
    private String SBM;
    private String PIC;
    private String ZD;

    public String getGWMC() {
        return GWMC;
    }

    public void setGWMC(String GWMC) {
        this.GWMC = GWMC;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }

    public String getYHZY() {
        return YHZY;
    }

    public void setYHZY(String YHZY) {
        this.YHZY = YHZY;
    }

    public String getZSXM() {
        return ZSXM;
    }

    public void setZSXM(String ZSXM) {
        this.ZSXM = ZSXM;
    }

    public String getDXGZWD() {
        return DXGZWD;
    }

    public void setDXGZWD(String DXGZWD) {
        this.DXGZWD = DXGZWD;
    }

    public String getSBM() {
        return SBM;
    }

    public void setSBM(String SBM) {
        this.SBM = SBM;
    }

    public String getPIC() {
        return PIC;
    }

    public void setPIC(String PIC) {
        this.PIC = PIC;
    }

    public String getZD() {
        return ZD;
    }

    public void setZD(String ZD) {
        this.ZD = ZD;
    }

    public String getPX() {
        return PX;
    }

    public void setPX(String PX) {
        this.PX = PX;
    }

    private String PX;

}
