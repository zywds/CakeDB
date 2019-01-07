package com.zhangyuwei.cake.entities;

public class NewUserInformation {
    int nId;//新增用户信息编号
    String nName;//姓名，收货人
    String nPhone;//电话号码,联系方式
    String nSen;
    String nCity;//城市
    String nSite;//位置(路名/写字楼/小区)
    String nDesc;//详细地址(楼号/门牌号)
    int rId;//外键(与用户注册信息表进行关联)   1
    int nState;
    Regsist regsist;

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public String getnPhone() {
        return nPhone;
    }

    public void setnPhone(String nPhone) {
        this.nPhone = nPhone;
    }

    public String getnSen() {
        return nSen;
    }

    public void setnSen(String nSen) {
        this.nSen = nSen;
    }

    public String getnCity() {
        return nCity;
    }

    public void setnCity(String nCity) {
        this.nCity = nCity;
    }

    public String getnSite() {
        return nSite;
    }

    public void setnSite(String nSite) {
        this.nSite = nSite;
    }

    public String getnDesc() {
        return nDesc;
    }

    public void setnDesc(String nDesc) {
        this.nDesc = nDesc;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public int getnState() {
        return nState;
    }

    public void setnState(int nState) {
        this.nState = nState;
    }

    public Regsist getRegsist() {
        return regsist;
    }

    public void setRegsist(Regsist regsist) {
        this.regsist = regsist;
    }

    @Override
    public String toString() {
        return "NewUserInformation{" +
                "nId=" + nId +
                ", nName='" + nName + '\'' +
                ", nPhone='" + nPhone + '\'' +
                ", nSen='" + nSen + '\'' +
                ", nCity='" + nCity + '\'' +
                ", nSite='" + nSite + '\'' +
                ", nDesc='" + nDesc + '\'' +
                ", rId=" + rId +
                ", nState=" + nState +
                ", regsist=" + regsist +
                '}';
    }
}
