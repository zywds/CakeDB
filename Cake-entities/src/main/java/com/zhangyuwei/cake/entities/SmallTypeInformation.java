package com.zhangyuwei.cake.entities;
/**
 *蛋糕与小类型对应表
 * */
public class SmallTypeInformation {
    int stiId;//蛋糕小类型编号
    int cId;//关联蛋糕表   1
    int stId;//关联蛋糕小类型表   2
    CakeInformation cakeInformation;//1
    SmallCakeType smallCakeType;//2
    public SmallTypeInformation(){

    }
    public SmallTypeInformation(int cId,int stId){
        this.cId=cId;
        this.stId=stId;
    }
    public int getStiId() {
        return stiId;
    }

    public void setStiId(int stiId) {
        this.stiId = stiId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    public CakeInformation getCakeInformation() {
        return cakeInformation;
    }

    public void setCakeInformation(CakeInformation cakeInformation) {
        this.cakeInformation = cakeInformation;
    }

    public SmallCakeType getSmallCakeType() {
        return smallCakeType;
    }

    public void setSmallCakeType(SmallCakeType smallCakeType) {
        this.smallCakeType = smallCakeType;
    }

    @Override
    public String toString() {
        return "SmallTypeInformation{" +
                "stiId=" + stiId +
                ", cId=" + cId +
                ", stId=" + stId +
                ", cakeInformation=" + cakeInformation +
                ", smallCakeType=" + smallCakeType +
                '}';
    }
}
