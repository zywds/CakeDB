package com.zhangyuwei.cake.entities;

import java.math.BigDecimal;

public class OrderingDesc {
    int odId;//订单详细编号
    BigDecimal odPrice;//商品价格
    int odPoundage;//磅数
    int odTableWare;//餐具
    BigDecimal odSumPrice;//总价
    int odNumber;//商品数量
    int oId;//关联订单表
    int cId;//关联蛋糕信息表，获得蛋糕的一些信息
    Ording ording;
    CakeInformation cakeInformation;
    public OrderingDesc(){

    }
    public OrderingDesc(BigDecimal odPrice,int odPoundage,int odTableWare,BigDecimal odSumPrice,int odNumber,int oId,int cId){
        this.odPrice=odPrice;
        this.odPoundage=odPoundage;
        this.odTableWare=odTableWare;
        this.odSumPrice=odSumPrice;
        this.odNumber=odNumber;
        this.oId=oId;
        this.cId=cId;
    }

    public int getOdId() {
        return odId;
    }

    public void setOdId(int odId) {
        this.odId = odId;
    }

    public BigDecimal getOdPrice() {
        return odPrice;
    }

    public void setOdPrice(BigDecimal odPrice) {
        this.odPrice = odPrice;
    }

    public int getOdPoundage() {
        return odPoundage;
    }

    public void setOdPoundage(int odPoundage) {
        this.odPoundage = odPoundage;
    }

    public int getOdTableWare() {
        return odTableWare;
    }

    public void setOdTableWare(int odTableWare) {
        this.odTableWare = odTableWare;
    }

    public BigDecimal getOdSumPrice() {
        return odSumPrice;
    }

    public void setOdSumPrice(BigDecimal odSumPrice) {
        this.odSumPrice = odSumPrice;
    }

    public int getOdNumber() {
        return odNumber;
    }

    public void setOdNumber(int odNumber) {
        this.odNumber = odNumber;
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public Ording getOrding() {
        return ording;
    }

    public void setOrding(Ording ording) {
        this.ording = ording;
    }

    public CakeInformation getCakeInformation() {
        return cakeInformation;
    }

    public void setCakeInformation(CakeInformation cakeInformation) {
        this.cakeInformation = cakeInformation;
    }

    @Override
    public String toString() {
        return "OrderingDesc{" +
                "odId=" + odId +
                ", odPrice=" + odPrice +
                ", odPoundage=" + odPoundage +
                ", odTableWare=" + odTableWare +
                ", odSumPrice=" + odSumPrice +
                ", odNumber=" + odNumber +
                ", oId=" + oId +
                ", cId=" + cId +
                ", ording=" + ording +
                ", cakeInformation=" + cakeInformation +
                '}';
    }
}
