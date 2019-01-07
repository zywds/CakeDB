package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.*;

import java.util.List;
import java.util.Map;

public interface IuserMainDao {
    //查询蛋糕有关的一切信息(多对多)
    List<SmallTypeInformation> selectCakeInformationAll(String stName);
    //查询所有商品(新品)
    List<CakeInformation> selectCakeInformation();
    //根据id查询出商品
    List<CakeInformation> selectCakeInformationByCId(int cId);
    //根据商品id查出小类型
    List<SmallTypeInformation> selectSmallTypeformationByCId(int cId);
    //根据id查出磅数
    List<PricePoundage> selectPricePoundageByCId(int cId);
    //根据id查出图片
    List<CakePicture> selectCakepicture(int cId);
    //查询商品口味
    List<MouseType> selectCakeInformationMouseType();
    //查询商品类型
    List<CakeType> selectCakeInformationCakeType();
    //查询蛋糕小类型表
    List<SmallCakeType> selectSmallCakeType();
    //查询所有商品（多对多）,不用分页，根据商品别和口味进行查询
    List<SmallTypeInformation> selectCakeInfomrationAllByctNameAndmtName(Map<String,String> map);
    //查询出所有商品，根据类型和口味
    List<CakeInformation> selectCakeInformationByctIdAndmtId(Map<String,String> map);
}
