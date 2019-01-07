package com.zhangyuwei.cake.entities;
/*蛋糕图片表*/
public class CakePicture {
    int cpId;//蛋糕图片编号
    String cpPicture;//蛋糕图片路径',
    String cpTitle;//图片标题
    int cId;//外键，关联蛋糕信息表   1
    CakeInformation cakeInformation;//1
    public CakePicture(){}
    public CakePicture(String cpPicture,int cId){
        this.cpPicture=cpPicture;
        this.cId=cId;
    }
    public int getCpId() {
        return cpId;
    }

    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    public String getCpPicture() {
        return cpPicture;
    }

    public void setCpPicture(String cpPicture) {
        this.cpPicture = cpPicture;
    }

    public String getCpTitle() {
        return cpTitle;
    }

    public void setCpTitle(String cpTitle) {
        this.cpTitle = cpTitle;
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
        return "CakePicture{" +
                "cpId=" + cpId +
                ", cpPicture='" + cpPicture + '\'' +
                ", cpTitle='" + cpTitle + '\'' +
                ", cId=" + cId +
                ", cakeInformation=" + cakeInformation +
                '}';
    }
}
