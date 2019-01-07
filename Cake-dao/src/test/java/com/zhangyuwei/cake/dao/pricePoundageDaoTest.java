package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.PricePoundage;
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
public class pricePoundageDaoTest {
    @Autowired
    IpricePoundageDao dao;
    //查询所有商品
    @Test
    public void selectCakeInformationAll(){
        System.out.println(dao.selectCakeInformationAll());
    }
    //查询商品所对应的价格磅数
    @Test
    public void selectPricePoundageById(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("cId",1);map.put("page",0);map.put("limit",4);
        System.out.println(dao.selectPricePoundageById(map));
    }
    //查询商品所对应的价格磅数数量
    @Test
    public void selectPricePoundageByIdCount(){
        System.out.println(dao.selectPricePoundageByIdCount(1));
    }
    //添加价格与磅数
    @Test
    public void insertPricePoundage(){
        //价格（Bigdecimal）ppPrice
        BigDecimal ppPrice=new BigDecimal(120);
        //磅数(Bigdecimal)ppPoundage
        BigDecimal ppPoundage=new BigDecimal(12);
        //ppPicture string
        String ppPicture="1.jpg";
        //ppSizeLeft int
        int ppSizeLeft=4;
        //ppSizeRight int
        int ppSizeRight=4;
        //ppMinPeople int
        int ppMinPeople=2;
        //ppMaxPeople int
        int ppMaxPeople=4;
        //ppTableWare int
        int ppTableWare=5;
        //ppTime(String)
        String ppTime="4:00";
        //cId
        int cId=1;
        PricePoundage pricePoundage=new PricePoundage();
        pricePoundage.setPpPrice(ppPrice);pricePoundage.setPpPoundage(ppPoundage);
        pricePoundage.setPpPicture(ppPicture);pricePoundage.setPpSizeLeft(ppSizeLeft);
        pricePoundage.setPpSizeRight(ppSizeRight);pricePoundage.setPpMinPeople(ppMinPeople);
        pricePoundage.setPpMaxPeople(ppMaxPeople);pricePoundage.setPpTableWare(ppTableWare);
        pricePoundage.setPpTime(ppTime);
        pricePoundage.setcId(cId);
        if(dao.insertPricePoundage(pricePoundage)>0){
            System.out.println("添加成功！");
        }
    }
    //删除价格磅数，根据价格磅数id和磅数(磅数唯一)
    @Test
    public void deletePricePoundageByIdAndPoundage(){
        int ppId=1;
        if(dao.deletePricePoundageByIdAndPoundage(ppId)>0){
            System.out.println("删除成功！");
        }
    }
    //修改价格磅数表，根据价格磅数id和磅数(磅数唯一)
    @Test
    public void updatePricePoundageByIdAndPoundage(){
        //价格（Bigdecimal）ppPrice
        BigDecimal ppPrice=new BigDecimal(120);
        //ppPicture string
        String ppPicture="1.jpg";
        //ppSizeLeft int
        int ppSizeLeft=4;
        //ppSizeRight int
        int ppSizeRight=4;
        //ppMinPeople int
        int ppMinPeople=2;
        //ppMaxPeople int
        int ppMaxPeople=4;
        //ppTableWare int
        int ppTableWare=5;
        //ppTime(String)
        String ppTime="4:00";
        //cId
        int cId=1;
        int ppId=1;
        //磅数(Bigdecimal)ppPoundage
        BigDecimal ppPoundage=new BigDecimal(1);
        PricePoundage pricePoundage=new PricePoundage();
        pricePoundage.setPpPrice(ppPrice);
        pricePoundage.setPpPicture(ppPicture);
        pricePoundage.setPpSizeLeft(ppSizeLeft);
        pricePoundage.setPpSizeRight(ppSizeRight);
        pricePoundage.setPpMinPeople(ppMinPeople);
        pricePoundage.setPpMaxPeople(ppMaxPeople);
        pricePoundage.setPpTableWare(ppTableWare);
        pricePoundage.setPpTime(ppTime);
        pricePoundage.setPpId(ppId);
        /*根据商品编号和磅数进行修改*/
        pricePoundage.setcId(cId);
        pricePoundage.setPpPoundage(ppPoundage);
        if(dao.updatePricePoundageByIdAndPoundage(pricePoundage)>0){
            System.out.println("修改成功！");
        }
    }
}
