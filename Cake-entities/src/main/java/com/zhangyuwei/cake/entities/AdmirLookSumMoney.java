package com.zhangyuwei.cake.entities;

import java.math.BigDecimal;

public class AdmirLookSumMoney {
    int number;
    BigDecimal sumPrice;
    String picture;
    String name;
    public AdmirLookSumMoney(){

    }
    public AdmirLookSumMoney(int number){
        this.number=number;
    }
    public AdmirLookSumMoney(BigDecimal sumPrice){
        this.sumPrice=sumPrice;
    }
    public AdmirLookSumMoney(int number,BigDecimal sumPrice){
        this.number=number;
        this.sumPrice=sumPrice;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdmirLookSumMoney{" +
                "number=" + number +
                ", sumPrice=" + sumPrice +
                ", picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
