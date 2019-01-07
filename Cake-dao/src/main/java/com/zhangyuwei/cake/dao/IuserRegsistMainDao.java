package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.*;

import java.util.List;

public interface IuserRegsistMainDao {
    //用户注册
    int insertRegsist(Regsist regsist);
    //用户登录
    int selectRegsist(Regsist regsist);
    //根据电话号码进行登录
    int selectRegsistByrPhone(String rPhone);
    //根据电话号码查询用户的编号
    int selectRegsistId(String rPhone);
    //加入购物车
    int insertShopping(Shopping shopping);
    //查询购物车用户卖相同商品的数量(注意状态)
    int selectCidState(Shopping shopping);
    //根据商品编号，磅数，购物车状态修改数量和总价
    int updateSumPrice(Shopping shopping);
    //查询购物车中的信息(根据用户编号和购物车状态)
    List<Shopping> selectShoppingByCidAndSstate(int rId);
    //查询赠送餐具数
    int selectPricePoundageByCidAndPoundage(PricePoundage pricePoundage);
    //数量减一
    int updateSnumberJian(int sId);
    //数量加一
    int updateSnumberJia(int sId);
    //删除一条
    int deleteShopping(int sId);
    //查询商品总额,当前用户，购物车状态
    String selectShoppingSumPrice(int rId);
    //添加用户（一个对应多个）
    int insertNewUserInformation(NewUserInformation newUserInformation);
    //查询所有用户所对应的用户
    List<NewUserInformation> selectNewUserInformation(int rId);
    //修改新增用户状态变为1
    int updateNewUserInformation(int nId);
    //修改新增用户状态全部变为0
    int updateNewUserInformation2(int rId);
    //根据状态查询用户信息
    List<NewUserInformation> selectNewUserInformationByNstate(int rId);
    //根据状态查询用户信息2
    List<NewUserInformation> selectNewUserInformationByNstate2(int rId);

}
