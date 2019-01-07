package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class userRegsistMainDaoTest {
    @Autowired
    IuserRegsistMainDao dao;
    //用户注册
    @Test
    public void insertRegsist(){
        Regsist regsist=new Regsist();
        regsist.setrPhone("12345323455");
        regsist.setrPassword("666666");
        if(dao.insertRegsist(regsist)>0){
            System.out.println("注册成功！");
        }
    }
    //用户登录
    @Test
    public void selectRegsist(){
        Regsist regsist=new Regsist();
        regsist.setrPhone("15992460506");regsist.setrPassword("6666606");
        if(dao.selectRegsist(regsist)==1){
            System.out.println("登录成功！");
        }
    }
    //根据电话号码进行登录
    @Test
    public void selectRegsistByrPhone(){
        String rPhone="15992460506";
        if(dao.selectRegsistByrPhone(rPhone)>0){
            System.out.println("登录成功！");
        }
    }
    //根据电话号码查询用户的编号
    @Test
    public void selectRegsistId(){
        System.out.println(dao.selectRegsistId("15992460506"));
    }
    //加入购物车
    @Test
    public void insertShopping(){
        //cId,sPrice,sPoundage,sSumPrice,sBoard,rId
        Shopping shopping=new Shopping();
        BigDecimal sPrice=new BigDecimal("196.0");
        shopping.setcId(2);shopping.setsPrice(sPrice);
        shopping.setsPoundage(1);shopping.setrId(1);
        shopping.setsBoard("");
        int count=dao.selectCidState(shopping);
        int counts=0;
        if((count+"").equals("0")){
            counts=1;
            BigDecimal sSumPrice=sPrice.multiply(new BigDecimal(counts));
            shopping.setsSumPrice(sSumPrice);
            if(dao.insertShopping(shopping)>0){
                System.out.println("添加成功！");
            }
        }else{
            if(dao.updateSumPrice(shopping)>0){
                System.out.println("修改成功！");
            }
        }
    }
    //查询购物车用户卖相同商品的数量(注意状态)
    @Test
    public void selectCidState(){
        Shopping shopping=new Shopping();
        shopping.setcId(1);shopping.setrId(1);shopping.setsPoundage(1);
        System.out.println(dao.selectCidState(shopping));
    }
    //根据商品编号，磅数，购物车状态修改数量和总价
    @Test
    public void updateSumPrice(){
        Shopping shopping=new Shopping();
        shopping.setcId(1);shopping.setrId(1);shopping.setsPoundage(1);
        if(dao.updateSumPrice(shopping)>0){
            System.out.println("修改成功！");
        }
    }
    //查询购物车中的信息(根据用户编号和购物车状态)
    @Test
    public void selectShoppingByCidAndSstate(){
        System.out.println(dao.selectShoppingByCidAndSstate(1));
    }
    //查询赠送餐具数
    @Test
    public void selectPricePoundageByCidAndPoundage(){
        PricePoundage pricePoundage=new PricePoundage();
        pricePoundage.setcId(1);pricePoundage.setPpPoundage(new BigDecimal(1.0));
        System.out.println(dao.selectPricePoundageByCidAndPoundage(pricePoundage));
    }
    //查询商品总额,当前用户，购物车状态
    @Test
    public void selectShoppingSumPrice(){
        System.out.println(dao.selectShoppingSumPrice(1));
    }
    //添加用户（一个对应多个）
    @Test
    public void insertNewUserI5nformation(){
        NewUserInformation newUserInformation=new NewUserInformation();
        newUserInformation.setnName("ddd");//名字
        newUserInformation.setnPhone("134545454");//电话
        newUserInformation.setnSen("dddd");//省
        newUserInformation.setnCity("dd");//县
        newUserInformation.setnSite("33");//乡
        newUserInformation.setnDesc("dd");//详细位置
        newUserInformation.setrId(1);//用户编号
        if(dao.insertNewUserInformation(newUserInformation)>0){
            System.out.println("添加成功！");
        }
    }
    //查询所有用户所对应的用户
    @Test
    public void selectNewUserInformation(){
        System.out.println(dao.selectNewUserInformation(1));
    }
    //修改新增用户状态
    @Test
    public void updateNewUserInformation(){
        if(dao.updateNewUserInformation(1)>0){
            System.out.println("修改成功！");
        }
    }
    //根据状态查询用户信息
    @Test
    public void selectNewUserInformationByNstate(){
        System.out.println(dao.selectNewUserInformationByNstate(1));
    }

}
