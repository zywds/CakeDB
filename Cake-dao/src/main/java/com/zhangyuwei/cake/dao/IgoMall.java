package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.GoMall;

import java.util.List;

public interface IgoMall {
    //查询所有的商品
    List<GoMall> selectAllgoMall();
    //删除商品根据编号
    int deletegoMallById(int gId);
    //添加商品
    int insertgoMall(GoMall goMall);
    //修改商品
    int updategoMall(GoMall goMall);
    //修改数量加一
    int updategoMallNumberAdd(int gId);
    //修改数量减一
    int updategoMallNumberRemove(int gId);
}
