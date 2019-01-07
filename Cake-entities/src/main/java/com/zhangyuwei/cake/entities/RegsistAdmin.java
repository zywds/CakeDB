package com.zhangyuwei.cake.entities;

import java.util.Date;

public class RegsistAdmin {
    int raId;
    String raName;
    String raPhone;
    String raEmail;
    String raPassword;
    String raRole;
    Date raBirthday;
    int raState;

    public int getRaId() {
        return raId;
    }

    public void setRaId(int raId) {
        this.raId = raId;
    }

    public String getRaName() {
        return raName;
    }

    public void setRaName(String raName) {
        this.raName = raName;
    }

    public String getRaPhone() {
        return raPhone;
    }

    public void setRaPhone(String raPhone) {
        this.raPhone = raPhone;
    }

    public String getRaEmail() {
        return raEmail;
    }

    public void setRaEmail(String raEmail) {
        this.raEmail = raEmail;
    }

    public String getRaPassword() {
        return raPassword;
    }

    public void setRaPassword(String raPassword) {
        this.raPassword = raPassword;
    }

    public String getRaRole() {
        return raRole;
    }

    public void setRaRole(String raRole) {
        this.raRole = raRole;
    }

    public Date getRaBirthday() {
        return raBirthday;
    }

    public void setRaBirthday(Date raBirthday) {
        this.raBirthday = raBirthday;
    }

    public int getRaState() {
        return raState;
    }

    public void setRaState(int raState) {
        this.raState = raState;
    }

    @Override
    public String toString() {
        return "RegsistAdmin{" +
                "raId=" + raId +
                ", raName='" + raName + '\'' +
                ", raPhone='" + raPhone + '\'' +
                ", raEmail='" + raEmail + '\'' +
                ", raPassword='" + raPassword + '\'' +
                ", raRole='" + raRole + '\'' +
                ", raBirthday=" + raBirthday +
                ", raState=" + raState +
                '}';
    }
}
