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

import java.util.*;

@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class cakeDaoMainTest {
    @Autowired
    IcakeMainDao dao;
    //查询蛋糕有关的一切信息
    @Test
    public void selectCakeInformationAll(){
        System.out.println(dao.selectCakeInformationAll());
    }
    //查询所有的商品
    @Test
    public void selectInformation(){
        System.out.println(dao.selectInformation());
    }
    //查询蛋糕类别，蛋糕口味和糕信息表结合
    @Test
    public void selectCaAndCaAndMo(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("page",0);map.put("limit",2);
        System.out.println(dao.selectCaAndCaAndMo(map));
    }
    //查询蛋糕类别，蛋糕口味和蛋糕信息表结合数量
    //@Test
    /*public void selectCaAndCaAndMoCount(){
        System.out.println(dao.selectCaAndCaAndMoCount());
    }*/
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
    //查询商品口味和商品类型
    @Test
    public void selectCaAndCaAndMoNoPage(){
        System.out.println(dao.selectCaAndCaAndMoNoPage());
    }
    //添加数据到蛋糕信息表
    @Test
    public void insertCakeInformation(){
        CakeInformation cakeInformation=new CakeInformation();
        cakeInformation.setcName("苹果");cakeInformation.setcNameEnglish("MangoJerrya");
        cakeInformation.setcDecription("优质芒果的三种姿态");cakeInformation.setcPicture("3.jpg");
        cakeInformation.setcDesc("利用富文本进行展示");cakeInformation.setCtId(1);
        cakeInformation.setMtId(2);
        int row=dao.insertCakeInformation(cakeInformation);
        if(row>0){
            System.out.println("添加成功！");
        }
    }
    //添加数据到蛋糕与蛋糕小类型对应表
    @Test
    public void insertSmallTypeInformation(){
        SmallTypeInformation smallTypeInformation=new SmallTypeInformation();
        smallTypeInformation.setcId(1);smallTypeInformation.setStId(3);
        int row=dao.insertSmallTypeInformation(smallTypeInformation);
        if(row>0){
            System.out.println("添加成功！");
        }
    }
    //添加数据到蛋糕与蛋糕小类型对应表(多添加)
    @Test
    public void insertSmallTypeInformationSome(){
        int[] arr={3,4};
        List<SmallTypeInformation> smallTypeInformationList=new ArrayList<SmallTypeInformation>();
        for (int i=0;i<2;i++){
            smallTypeInformationList.add(new SmallTypeInformation(1,arr[i]));
        }
        if(dao.insertSmallTypeInformationSome(smallTypeInformationList)>=2){
            System.out.println("添加成功!");
        }

    }
    //获得最后一条数据的ID
    @Test
    public void selectLastId(){
        System.out.println(dao.selectLastId().get(dao.selectLastId().size()-1).getcId());
        //System.out.println(dao.selectLastId().size());
    }
    //查询蛋糕小类型表
    @Test
    public void selectSmallCakeType(){
        System.out.println(dao.selectSmallCakeType());
    }
    //根据蛋糕id查出蛋糕与蛋糕下类型对应表
    @Test
    public void selectSmallTypeInformation(){
        int cId=1;
        System.out.println(dao.selectSmallTypeInformation(cId));
    }
    //修改蛋糕信息
    @Test
    public void updateCakeInformationAll(){
        CakeInformation cakeInformation=new CakeInformation();
        cakeInformation.setcName("苹果");cakeInformation.setcNameEnglish("MangoJerrya");
        cakeInformation.setcDecription("优质芒果的三种姿态");cakeInformation.setcPicture("3.jpg");
        cakeInformation.setcDesc("利用富文本进行展示");cakeInformation.setCtId(1);
        cakeInformation.setMtId(2);
        int row=dao.updateCakeInformationAll(cakeInformation);
        if(row>0){
            System.out.println("修改成功！");
        }
    }
    //修改蛋糕与蛋糕小类型对应表
    @Test
    public void updateSmallTypeInformation(){
        int[] arr={1,2,3};
        SmallTypeInformation smallTypeInformation=new SmallTypeInformation();
        int row=0;
        for (int i=0;i<arr.length;i++){
            smallTypeInformation.setcId(1);smallTypeInformation.setStId(arr[i]);
            dao.updateSmallTypeInformation(smallTypeInformation);row++;
        }
        if(row==arr.length){
            System.out.println("修改成功！");
        }
    }
    //根据id查询详情(富文本内容，不可以用url进行传值)
    @Test
    public void selectCakeInformationDescById(){
        int cId=1;
        System.out.println(dao.selectCakeInformationDescById(cId));
    }
    //根据ID删除蛋糕与小类型对应表中的数据
    @Test
    public void deleteSmallTypeInformationbyId(){
        int cId=1;
        if(dao.deleteSmallTypeInformationbyId(cId)>0){
            System.out.println("删除成功！");
        }
    }
}
