package xyz.seekyou.bank.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class LogBean {
    private int logid;
    private String logtype;
    private BigDecimal amount;
    private int userid;
    private Timestamp logtime;

    public int getLogid() {
        return logid;
    }

    public void setLogid(int logid) {
        this.logid = logid;
    }

    public String getLogtype() {
        return logtype;
    }

    public void setLogtype(String logtype) {
        this.logtype = logtype;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String[] toStringArray(){
        String[] t=new String[4];
        t[0]=String.valueOf(this.logid);
        t[1]=String.valueOf(this.userid);
        t[2]=String.valueOf(this.logtype);
        t[3]=String.valueOf(this.amount);
        return t;
    }

    public Timestamp getLogtime() {
        return logtime;
    }

    public void setLogtime(Timestamp logtime) {
        this.logtime = logtime;
    }
}
