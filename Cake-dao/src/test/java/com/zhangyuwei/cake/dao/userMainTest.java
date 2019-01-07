package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.CakeInformation;
import com.zhangyuwei.cake.entities.SmallTypeInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class userMainTest {
    @Autowired
    IuserMainDao dao;
    //查询蛋糕有关的一切信息
    @Test
    public void selectCakeInformationAll(){
        String stName="儿童";
        System.out.println(dao.selectCakeInformationAll(stName));
    }
    //查询蛋糕有关的一切信息(新品)
    @Test
    public void selectCakeInformation(){
        System.out.println(dao.selectCakeInformation());
    }
    //根据商品id查出小类型
    @Test
    public void selectSmallTypeformationByCId(){
        System.out.println(dao.selectSmallTypeformationByCId(1));
    }
    //根据id查出磅数
    @Test
    public void selectPricePoundageByCId(){
        System.out.println(dao.selectPricePoundageByCId(1));
    }
    //根据id查出图片
    @Test
    public void selectCakepicture(){
        System.out.println(dao.selectCakepicture(1));
    }
    //查询商品口味
    @Test
    public void selectCakeInformationMouseType(){
        System.out.println(dao.selectCakeInformationMouseType());
    }
    //查询商品类型
    @Test
    public void selectCakeInformationCakeType(){
        System.out.println(dao.selectCakeInformationCakeType());
    }
    //查询蛋糕小类型表
    @Test
    public void selectSmallCakeType(){
        System.out.println(dao.selectSmallCakeType());
    }
    //查询出所有商品，根据类型和口味
    @Test
    public void selectCakeInformationByctIdAndmtId(){
        Map<String,String> map=new HashMap<String, String>();
        map.put("ctId","1");map.put("mtId","0");
        System.out.println(dao.selectCakeInformationByctIdAndmtId(map));

    }
}
