package com.zhangyuwei.cake.entities;
/**
 *蛋糕小类型表
 * */
public class SmallCakeType {
    int stId;//蛋糕小类型编号
    String stName;//蛋糕小类型名称

    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    @Override
    public String toString() {
        return "SmallCakeType{" +
                "stId=" + stId +
                ", stName='" + stName + '\'' +
                '}';
    }
}
