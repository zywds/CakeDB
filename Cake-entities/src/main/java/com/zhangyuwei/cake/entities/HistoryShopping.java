package com.zhangyuwei.cake.entities;

import java.math.BigDecimal;

public class HistoryShopping {
    int sId;//购物车编号
    int cId;//商品编号
    BigDecimal sPrice;//单价
    int sPoundage;//磅数
    int sNumber;//数量
    BigDecimal sSumPrice;//总价
    int sState;//状态
    String sBoard;//生日牌
    int rId;//用户编号
    CakeInformation cakeInformation;//关联商品表
    Regsist regsist;//关联用户表

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public BigDecimal getsPrice() {
        return sPrice;
    }

    public void setsPrice(BigDecimal sPrice) {
        this.sPrice = sPrice;
    }

    public int getsPoundage() {
        return sPoundage;
    }

    public void setsPoundage(int sPoundage) {
        this.sPoundage = sPoundage;
    }

    public int getsNumber() {
        return sNumber;
    }

    public void setsNumber(int sNumber) {
        this.sNumber = sNumber;
    }

    public BigDecimal getsSumPrice() {
        return sSumPrice;
    }

    public void setsSumPrice(BigDecimal sSumPrice) {
        this.sSumPrice = sSumPrice;
    }

    public int getsState() {
        return sState;
    }

    public void setsState(int sState) {
        this.sState = sState;
    }

    public String getsBoard() {
        return sBoard;
    }

    public void setsBoard(String sBoard) {
        this.sBoard = sBoard;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public CakeInformation getCakeInformation() {
        return cakeInformation;
    }

    public void setCakeInformation(CakeInformation cakeInformation) {
        this.cakeInformation = cakeInformation;
    }

    public Regsist getRegsist() {
        return regsist;
    }

    public void setRegsist(Regsist regsist) {
        this.regsist = regsist;
    }

    @Override
    public String toString() {
        return "HistoryShopping{" +
                "sId=" + sId +
                ", cId=" + cId +
                ", sPrice=" + sPrice +
                ", sPoundage=" + sPoundage +
                ", sNumber=" + sNumber +
                ", sSumPrice=" + sSumPrice +
                ", sState=" + sState +
                ", sBoard='" + sBoard + '\'' +
                ", rId=" + rId +
                ", cakeInformation=" + cakeInformation +
                ", regsist=" + regsist +
                '}';
    }
}
