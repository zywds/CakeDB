package com.zhangyuwei.cake.entities;


/**
 *蛋糕信息表
 * */
public class CakeInformation {
    int cId;//蛋糕编号
    String cName;//蛋糕名称中文
    String cNameEnglish;//蛋糕英文名称
    String cDecription;//蛋糕首页简单描述
    String cPicture;//蛋糕默认图片
    int cState;//蛋糕状态(1:上架 0:下架)
    String cDesc;//蛋糕详情（利用富文本进行展示）
    int ctId;//关联商品类别表   1
    int mtId;//关联蛋糕口味表   2
    CakeType cakeType;//1
    MouseType mouseType;//2

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcNameEnglish() {
        return cNameEnglish;
    }

    public void setcNameEnglish(String cNameEnglish) {
        this.cNameEnglish = cNameEnglish;
    }

    public String getcDecription() {
        return cDecription;
    }

    public void setcDecription(String cDecription) {
        this.cDecription = cDecription;
    }

    public String getcPicture() {
        return cPicture;
    }

    public void setcPicture(String cPicture) {
        this.cPicture = cPicture;
    }

    public int getcState() {
        return cState;
    }

    public void setcState(int cState) {
        this.cState = cState;
    }

    public String getcDesc() {
        return cDesc;
    }

    public void setcDesc(String cDesc) {
        this.cDesc = cDesc;
    }

    public int getCtId() {
        return ctId;
    }

    public void setCtId(int ctId) {
        this.ctId = ctId;
    }

    public int getMtId() {
        return mtId;
    }

    public void setMtId(int mtId) {
        this.mtId = mtId;
    }

    public CakeType getCakeType() {
        return cakeType;
    }

    public void setCakeType(CakeType cakeType) {
        this.cakeType = cakeType;
    }

    public MouseType getMouseType() {
        return mouseType;
    }

    public void setMouseType(MouseType mouseType) {
        this.mouseType = mouseType;
    }

    @Override
    public String toString() {
        return "CakeInformation{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cNameEnglish='" + cNameEnglish + '\'' +
                ", cDecription=" + cDecription +
                ", cPicture='" + cPicture + '\'' +
                ", cState=" + cState +
                ", cDesc='" + cDesc + '\'' +
                ", ctId=" + ctId +
                ", mtId=" + mtId +
                ", cakeType=" + cakeType +
                ", mouseType=" + mouseType +
                '}';
    }
}
