package com.zhangyuwei.cake.entities;
/**
 * 蛋糕口味表
 * */
public class MouseType {
    int mtId;//蛋糕口味编号
    String mtName;//蛋糕口味名称

    public int getMtId() {
        return mtId;
    }

    public void setMtId(int mtId) {
        this.mtId = mtId;
    }

    public String getMtName() {
        return mtName;
    }

    public void setMtName(String mtName) {
        this.mtName = mtName;
    }

    @Override
    public String toString() {
        return "MouseType{" +
                "mtId=" + mtId +
                ", mtName='" + mtName + '\'' +
                '}';
    }
}
