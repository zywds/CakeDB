package com.zhangyuwei.cake.entities;

import java.util.Date;

public class Regsist {
    int rId;
    String rPhone;
    String rPassword;
    Date rBirthday;
    int rState;

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getrPhone() {
        return rPhone;
    }

    public void setrPhone(String rPhone) {
        this.rPhone = rPhone;
    }

    public String getrPassword() {
        return rPassword;
    }

    public void setrPassword(String rPassword) {
        this.rPassword = rPassword;
    }

    public Date getrBirthday() {
        return rBirthday;
    }

    public void setrBirthday(Date rBirthday) {
        this.rBirthday = rBirthday;
    }

    public int getrState() {
        return rState;
    }

    public void setrState(int rState) {
        this.rState = rState;
    }

    @Override
    public String toString() {
        return "Regsist{" +
                "rId=" + rId +
                ", rPhone='" + rPhone + '\'' +
                ", rPassword='" + rPassword + '\'' +
                ", rBirthday=" + rBirthday +
                ", rState=" + rState +
                '}';
    }
}
