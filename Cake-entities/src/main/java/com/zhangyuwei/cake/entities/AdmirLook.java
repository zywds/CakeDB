package com.zhangyuwei.cake.entities;

import java.math.BigDecimal;

public class AdmirLook {
    int sumNumber;//商品数量
    int sallNumber;//所售商品数量
    BigDecimal sumSallPrice;//总销售额
    String maxName;//销售最多商品
    String minName;//销售最少商品
    String admirName;//管理员

    public int getSumNumber() {
        return sumNumber;
    }

    public void setSumNumber(int sumNumber) {
        this.sumNumber = sumNumber;
    }

    public int getSallNumber() {
        return sallNumber;
    }

    public void setSallNumber(int sallNumber) {
        this.sallNumber = sallNumber;
    }

    public BigDecimal getSumSallPrice() {
        return sumSallPrice;
    }

    public void setSumSallPrice(BigDecimal sumSallPrice) {
        this.sumSallPrice = sumSallPrice;
    }

    public String getMaxName() {
        return maxName;
    }

    public void setMaxName(String maxName) {
        this.maxName = maxName;
    }

    public String getMinName() {
        return minName;
    }

    public void setMinName(String minName) {
        this.minName = minName;
    }

    public String getAdmirName() {
        return admirName;
    }

    public void setAdmirName(String admirName) {
        this.admirName = admirName;
    }

    @Override
    public String toString() {
        return "AdmirLook{" +
                "sumNumber=" + sumNumber +
                ", sallNumber=" + sallNumber +
                ", sumSallPrice=" + sumSallPrice +
                ", maxName='" + maxName + '\'' +
                ", minName='" + minName + '\'' +
                ", admirName='" + admirName + '\'' +
                '}';
    }
}
