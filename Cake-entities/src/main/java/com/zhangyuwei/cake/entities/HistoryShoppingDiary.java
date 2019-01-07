package com.zhangyuwei.cake.entities;

import java.util.Date;

public class HistoryShoppingDiary {
    int hdId;//迁移编号
    Date htTime;//迁移时间
    int htCount;//迁移数量
    String htState;//迁移状态(迁移成功)
    String htAdmir;//迁移人

    public int getHdId() {
        return hdId;
    }

    public void setHdId(int hdId) {
        this.hdId = hdId;
    }

    public Date getHtTime() {
        return htTime;
    }

    public void setHtTime(Date htTime) {
        this.htTime = htTime;
    }

    public int getHtCount() {
        return htCount;
    }

    public void setHtCount(int htCount) {
        this.htCount = htCount;
    }

    public String getHtState() {
        return htState;
    }

    public void setHtState(String htState) {
        this.htState = htState;
    }

    public String getHtAdmir() {
        return htAdmir;
    }

    public void setHtAdmir(String htAdmir) {
        this.htAdmir = htAdmir;
    }

    @Override
    public String toString() {
        return "HistoryShoppingDiary{" +
                "hdId=" + hdId +
                ", htTime=" + htTime +
                ", htCount=" + htCount +
                ", htState='" + htState + '\'' +
                ", htAdmir='" + htAdmir + '\'' +
                '}';
    }
}
