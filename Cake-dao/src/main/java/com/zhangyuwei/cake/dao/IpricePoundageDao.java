package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.CakeInformation;
import com.zhangyuwei.cake.entities.PricePoundage;

import java.util.List;
import java.util.Map;

/**
 * 价格，磅数接口
 * */
public interface IpricePoundageDao {
    //查询所有商品
    List<CakeInformation> selectCakeInformationAll();
    //查询商品所对应的价格磅数
    List<PricePoundage> selectPricePoundageById(Map<String,Object> map);
    //查询商品所对应的价格磅数数量
    int selectPricePoundageByIdCount(int cId);
    //添加价格与磅数
    int insertPricePoundage(PricePoundage pricePoundage);
    //删除价格磅数，根据价格磅数id和磅数(磅数唯一)
    int deletePricePoundageByIdAndPoundage(int ppId);
    //修改价格磅数表，根据价格磅数id
    int updatePricePoundageByIdAndPoundage(PricePoundage pricePoundage);
}
