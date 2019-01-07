package com.zhangyuwei.cake.entities;

import java.util.Date;

public class Ording {
    int oId;//订单编号
    String oNo;//订单号。时间戳
    Date oTime;//订单时间(默认)
    Date oPTime;//配送时间
    String oAction;//配送方式
    String oDesc;//备注
    int ostate;//订单状态(0:待付款1：已完成 2:已取消)
    int oAstate;//管理员管理订单状态(0:代发货，1：待配送，2:待收货 3：已收货)
    String oName;//新增用户姓名
    String oPhone;//新增用户电话
    String oAddress;//新增用户地址
    String oPayAction;//支付方式
    int rId;//关联用户注册表
    Regsist regsist;

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public String getoNo() {
        return oNo;
    }

    public void setoNo(String oNo) {
        this.oNo = oNo;
    }

    public Date getoTime() {
        return oTime;
    }

    public void setoTime(Date oTime) {
        this.oTime = oTime;
    }

    public Date getoPTime() {
        return oPTime;
    }

    public void setoPTime(Date oPTime) {
        this.oPTime = oPTime;
    }

    public String getoAction() {
        return oAction;
    }

    public void setoAction(String oAction) {
        this.oAction = oAction;
    }

    public String getoDesc() {
        return oDesc;
    }

    public void setoDesc(String oDesc) {
        this.oDesc = oDesc;
    }

    public int getOstate() {
        return ostate;
    }

    public void setOstate(int ostate) {
        this.ostate = ostate;
    }

    public int getoAstate() {
        return oAstate;
    }

    public void setoAstate(int oAstate) {
        this.oAstate = oAstate;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public String getoPhone() {
        return oPhone;
    }

    public void setoPhone(String oPhone) {
        this.oPhone = oPhone;
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress;
    }

    public String getoPayAction() {
        return oPayAction;
    }

    public void setoPayAction(String oPayAction) {
        this.oPayAction = oPayAction;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public Regsist getRegsist() {
        return regsist;
    }

    public void setRegsist(Regsist regsist) {
        this.regsist = regsist;
    }

    @Override
    public String toString() {
        return "Ording{" +
                "oId=" + oId +
                ", oNo='" + oNo + '\'' +
                ", oTime=" + oTime +
                ", oPTime=" + oPTime +
                ", oAction='" + oAction + '\'' +
                ", oDesc='" + oDesc + '\'' +
                ", ostate=" + ostate +
                ", oAstate=" + oAstate +
                ", oName='" + oName + '\'' +
                ", oPhone='" + oPhone + '\'' +
                ", oAddress='" + oAddress + '\'' +
                ", oPayAction='" + oPayAction + '\'' +
                ", rId=" + rId +
                ", regsist=" + regsist +
                '}';
    }
}
