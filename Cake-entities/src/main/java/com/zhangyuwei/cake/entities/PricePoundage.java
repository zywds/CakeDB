package com.zhangyuwei.cake.entities;

import java.math.BigDecimal;

/**
 * 价格、磅数表
 * */
public class PricePoundage {
    int ppId;//价格，磅数编号
    BigDecimal ppPrice;//价格
    BigDecimal ppPoundage;//磅数
    String ppPicture;//价格，磅数图片
    int ppSizeLeft;//大小(19cm*29cm)
    int ppSizeRight;//大小(19cm*29cm)
    int ppMinPeople;//最低分享人数
    int ppMaxPeople;//最大分享人数
    int ppTableWare;//餐具数量
    String ppTime;//最早配送时间
    int cId;//外键，关联蛋糕信息表   1
    CakeInformation cakeInformation;//1

    public int getPpId() {
        return ppId;
    }

    public void setPpId(int ppId) {
        this.ppId = ppId;
    }

    public BigDecimal getPpPrice() {
        return ppPrice;
    }

    public void setPpPrice(BigDecimal ppPrice) {
        this.ppPrice = ppPrice;
    }

    public BigDecimal getPpPoundage() {
        return ppPoundage;
    }

    public void setPpPoundage(BigDecimal ppPoundage) {
        this.ppPoundage = ppPoundage;
    }

    public String getPpPicture() {
        return ppPicture;
    }

    public void setPpPicture(String ppPicture) {
        this.ppPicture = ppPicture;
    }

    public int getPpSizeLeft() {
        return ppSizeLeft;
    }

    public void setPpSizeLeft(int ppSizeLeft) {
        this.ppSizeLeft = ppSizeLeft;
    }

    public int getPpSizeRight() {
        return ppSizeRight;
    }

    public void setPpSizeRight(int ppSizeRight) {
        this.ppSizeRight = ppSizeRight;
    }

    public int getPpMinPeople() {
        return ppMinPeople;
    }

    public void setPpMinPeople(int ppMinPeople) {
        this.ppMinPeople = ppMinPeople;
    }

    public int getPpMaxPeople() {
        return ppMaxPeople;
    }

    public void setPpMaxPeople(int ppMaxPeople) {
        this.ppMaxPeople = ppMaxPeople;
    }

    public int getPpTableWare() {
        return ppTableWare;
    }

    public void setPpTableWare(int ppTableWare) {
        this.ppTableWare = ppTableWare;
    }

    public String getPpTime() {
        return ppTime;
    }

    public void setPpTime(String ppTime) {
        this.ppTime = ppTime;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public CakeInformation getCakeInformation() {
        return cakeInformation;
    }

    public void setCakeInformation(CakeInformation cakeInformation) {
        this.cakeInformation = cakeInformation;
    }

    @Override
    public String toString() {
        return "PricePoundage{" +
                "ppId=" + ppId +
                ", ppPrice=" + ppPrice +
                ", ppPoundage=" + ppPoundage +
                ", ppPicture='" + ppPicture + '\'' +
                ", ppSizeLeft=" + ppSizeLeft +
                ", ppSizeRight=" + ppSizeRight +
                ", ppMinPeople=" + ppMinPeople +
                ", ppMaxPeople=" + ppMaxPeople +
                ", ppTableWare=" + ppTableWare +
                ", ppTime='" + ppTime + '\'' +
                ", cId=" + cId +
                ", cakeInformation=" + cakeInformation +
                '}';
    }
}
