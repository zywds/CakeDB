package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.AdmirLookSumMoney;
import com.zhangyuwei.cake.entities.OrderingDesc;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@SuppressWarnings("ALL")
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class admirOrderingLookTest {
    @Autowired
    IadmirOrderingLook dao;
    //查询所有商品总数
    @Test
    public void selectSumNumber(){
        System.out.println(dao.selectSumNumber());
    }
    //查询所售商品
    @Test
    public void selectAllOrderingDesc(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("year","2018");map.put("month","12");map.put("day","26");
        System.out.println(dao.selectAllOrderingDesc(map));
    }
    //查询出销售数量最多的商品
    @Test
    public void selectMaxName(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("year","2018");map.put("month","12");map.put("day","25");
        System.out.println(dao.selectMaxName(map));
    }
    //查询出销售数量最少的商品
    @Test
    public void selectMinName(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("year","2018");map.put("month","12");map.put("day","26");
        System.out.println(dao.selectMinName(map));
    }
    //查询所有销售商品的总价和数量
    @Test
    public void selectOrderingDescSumPriceAndNumber(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("year","2018");map.put("month","12");map.put("day","26");
        List<OrderingDesc> orderingDescList=dao.selectOrderingDescSumPriceAndNumber(map);
        int[] cId=new int[orderingDescList.size()];
        for(int i=0;i<orderingDescList.size();i++){
            cId[i]=orderingDescList.get(i).getcId();
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<cId.length; i++) {
            if(!list.contains(cId[i])) {
                list.add(cId[i]);
            }
        }
        List<AdmirLookSumMoney> lists2=new ArrayList<AdmirLookSumMoney>();
        for(int j=0;j<list.size();j++){
            AdmirLookSumMoney lists=new AdmirLookSumMoney();
            int number=0;
            BigDecimal bigDecimal= new BigDecimal(0);
            String picture="";
            for(int i=0;i<orderingDescList.size();i++){
                if(orderingDescList.get(i).getcId()==list.get(j)){
                    number+=orderingDescList.get(i).getOdNumber();
                    bigDecimal=bigDecimal.add(orderingDescList.get(i).getOdSumPrice());
                    picture=orderingDescList.get(i).getCakeInformation().getcPicture();
                }
            }
            lists.setNumber(number);
            lists.setPicture(picture);
            lists.setSumPrice(bigDecimal);
            lists2.add(lists);
        }
        System.out.println(lists2);
    }
    //查询所售商品更具年份和月份，查出所售商品数量
    @Test
    public void selectAllOrderingDescByYearAndMonth(){
        //List<OrderingDesc> list=dao.selectAllOrderingDescByYearAndMonth(map);
        List<AdmirLookSumMoney> admirLookSumMoney=new ArrayList<AdmirLookSumMoney>();
        for (int i=0;i<12;i++){
            BigDecimal bigDecimal=new BigDecimal(0);
            int number=0;
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("year","2018");
            map.put("month",i+1);
            if(dao.selectAllOrderingDescByYearAndMonth(map).size()>0){
                for (int j=0;j<dao.selectAllOrderingDescByYearAndMonth(map).size();j++) {
                    bigDecimal = bigDecimal.add(dao.selectAllOrderingDescByYearAndMonth(map).get(j).getOdSumPrice());
                    number += dao.selectAllOrderingDescByYearAndMonth(map).get(j).getOdNumber();
                }
            }
            admirLookSumMoney.add(new AdmirLookSumMoney(number,bigDecimal));
        }
        System.out.println(admirLookSumMoney);
    }
}
