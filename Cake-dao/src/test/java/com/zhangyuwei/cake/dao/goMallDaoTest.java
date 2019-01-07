package com.zhangyuwei.cake.dao;

import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.zhangyuwei.cake.entities.CakeInformation;
import com.zhangyuwei.cake.entities.GoMall;
import com.zhangyuwei.cake.entities.SmallTypeInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class goMallDaoTest {
    @Autowired
    IgoMall dao;
    //查询所有的商品
    @Test
    public void selectAllgoMall(){
        System.out.println(dao.selectAllgoMall());
    }
    //删除商品根据编号
    @Test
    public void deletegoMallById(){
        System.out.println(dao.deletegoMallById(1));
    }
    //添加商品
    @Test
    public void insertgoMall(){
        GoMall goMall=new GoMall();
        BigDecimal gPrice=new BigDecimal("12.9");
        int gNumber=4;
        BigDecimal gSumPrice=gPrice.multiply(new BigDecimal(gNumber));
        goMall.setgName("laozhang");goMall.setgPrice(gPrice);
        goMall.setgNumber(gNumber);goMall.setgSumPrice(gSumPrice);
        if(dao.insertgoMall(goMall)>0){
            System.out.println("添加成功！");
        }
    }
    //修改商品
    @Test
    public void updategoMall(){
        GoMall goMall=new GoMall();
        BigDecimal gPrice=new BigDecimal("12.9");
        int gNumber=4;
        goMall.setgName("laozhang");goMall.setgPrice(gPrice);
        goMall.setgNumber(gNumber);
        goMall.setgId(1);
        if(dao.updategoMall(goMall)>0){
            System.out.println("修改成功！");
        }
    }
    //修改数量加一
    @Test
    public void updategoMallNumberAdd(){
        if(dao.updategoMallNumberAdd(1)>0){
            System.out.println("修改成功！");
        }
    }
    //修改数量减一
    @Test
    public void updategoMallNumberRemove(){
        if(dao.updategoMallNumberRemove(1)>0){
            System.out.println("修改成功！");
        }
    }
}
