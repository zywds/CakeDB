package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.*;

import java.util.List;
import java.util.Map;

/*蛋糕信息*/
public interface IcakeMainDao {
    //查询蛋糕有关的一切信息(多对多)
    List<SmallTypeInformation> selectCakeInformationAll();
    /*查询所有的商品*/
    List<CakeInformation> selectInformation();
    //查询蛋糕类别，蛋糕口味和蛋糕信息表结合
    List<CakeInformation> selectCaAndCaAndMo(Map<String,Object> objectMap);
    //查询蛋糕类别，蛋糕口味和蛋糕信息表结合数量
    int selectCaAndCaAndMoCount(Map<String,Object> objectMap);
    //修改蛋糕状态
    int updateCakeInformation(CakeInformation cakeInformation);
    //查询商品口味
    List<MouseType> selectCakeInformationMouseType();
    //查询商品口味和商品类型
    List<CakeInformation> selectCaAndCaAndMoNoPage();
    //查询商品类型
    List<CakeType> selectCakeInformationCakeType();
    //添加数据到蛋糕信息表
    int insertCakeInformation(CakeInformation cakeInformation);
    //添加数据到蛋糕与蛋糕小类型对应表
    int insertSmallTypeInformation(SmallTypeInformation smallTypeInformation);
    //添加数据到蛋糕与蛋糕小类型对应表(多添加)
    int insertSmallTypeInformationSome(List<SmallTypeInformation> smallTypeInformations);
    //获得最后一条数据的ID
    List<CakeInformation> selectLastId();
    //查询蛋糕小类型表
    List<SmallCakeType> selectSmallCakeType();
    //根据蛋糕id查出蛋糕与蛋糕下类型对应表
    List<SmallTypeInformation> selectSmallTypeInformation(int cId);
    //修改蛋糕信息
    int updateCakeInformationAll(CakeInformation cakeInformation);
    //修改蛋糕与蛋糕小类型对应表
    int updateSmallTypeInformation(SmallTypeInformation smallTypeInformation);
    //根据id查询详情
    List<CakeInformation> selectCakeInformationDescById(int cId);
   //根据ID删除蛋糕与小类型对应表中的数据
    int deleteSmallTypeInformationbyId(int cId);
}
