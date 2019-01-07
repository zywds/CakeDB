package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.CakeInformation;
import com.zhangyuwei.cake.entities.OrderingDesc;

import java.util.List;
import java.util.Map;

public interface IadmirOrderingLook {
    //查询所有商品总数
    int selectSumNumber();
    //查询所售商品
    List<OrderingDesc> selectAllOrderingDesc(Map<String,Object> map);
    //查询出销售数量最多的商品
    List<OrderingDesc> selectMaxName(Map<String,Object> map);
    //查询出销售数量最少的商品
    List<OrderingDesc> selectMinName(Map<String,Object> map);
    //查询所有销售商品的总价和数量
    List<OrderingDesc> selectOrderingDescSumPriceAndNumber(Map<String,Object> map);
    //查询所售商品更具年份和月份，查出所售商品数量
    List<OrderingDesc> selectAllOrderingDescByYearAndMonth(Map<String,Object> map);
}
