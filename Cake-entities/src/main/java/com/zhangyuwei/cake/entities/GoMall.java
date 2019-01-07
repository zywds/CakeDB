package com.zhangyuwei.cake.entities;

import java.math.BigDecimal;

public class GoMall {
    int gId;
    String gName;
    BigDecimal gPrice;
    int gNumber;
    BigDecimal gSumPrice;

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public BigDecimal getgPrice() {
        return gPrice;
    }

    public void setgPrice(BigDecimal gPrice) {
        this.gPrice = gPrice;
    }

    public int getgNumber() {
        return gNumber;
    }

    public void setgNumber(int gNumber) {
        this.gNumber = gNumber;
    }

    public BigDecimal getgSumPrice() {
        return gSumPrice;
    }

    public void setgSumPrice(BigDecimal gSumPrice) {
        this.gSumPrice = gSumPrice;
    }

    @Override
    public String toString() {
        return "GoMall{" +
                "gId=" + gId +
                ", gName='" + gName + '\'' +
                ", gPrice=" + gPrice +
                ", gNumber=" + gNumber +
                ", gSumPrice=" + gSumPrice +
                '}';
    }
}
