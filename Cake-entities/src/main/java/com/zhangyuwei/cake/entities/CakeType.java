package com.zhangyuwei.cake.entities;
/**
 * 商品类别表
 * */
public class CakeType {
    int ctId;//商品类别编号
    String ctName;//商品类别名称

    public int getCtId() {
        return ctId;
    }

    public void setCtId(int ctId) {
        this.ctId = ctId;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    @Override
    public String toString() {
        return "CakeType{" +
                "ctId=" + ctId +
                ", ctName='" + ctName + '\'' +
                '}';
    }
}
