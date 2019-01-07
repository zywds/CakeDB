package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.CakePicture;
import com.zhangyuwei.cake.entities.SmallTypeInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class imageDaoTest {
    @Autowired
    IimageDao dao;
    //查询所有商品
    @Test
    public void selectCakeInformation(){
        System.out.println(dao.selectCakeInformation());
    }
    //查询图片表，根据商品id
    @Test
    public void selectCakePicture(){
        CakePicture cakePicture=new CakePicture();
        cakePicture.setcId(1);cakePicture.setCpPicture("");
        System.out.println(dao.selectCakePicture(cakePicture));
    }
    //查询图片表中商品对应的图片数量
    @Test
    public void selectCakePictureCount(){
        System.out.println(dao.selectCakePictureCount(1));
    }
    //上传图片
    @Test
    public void insertCakePicture(){
        CakePicture cakePicture=new CakePicture();
        cakePicture.setCpPicture("1.jpg");
        cakePicture.setcId(1);
        if(dao.insertCakePicture(cakePicture)>0){
            System.out.println("添加成功!");
        }
    }
    //上传图片多
    @Test
    public void insertCakePictureSome(){
        String[] arr={"1.jpg","2.jpg"};
        List<CakePicture> cakePictureList=new ArrayList<CakePicture>();
        for (int i=0;i<arr.length;i++){
            cakePictureList.add(new CakePicture(arr[i],1));
        }
        if(dao.insertCakePictureSome(cakePictureList)>=arr.length){
            System.out.println("添加成功!");
        }
    }
    //修改图片
    @Test
    public void updateCakePictureById(){
        CakePicture cakePicture=new CakePicture();
        cakePicture.setCpTitle("dddd");
        cakePicture.setCpPicture("1.jpg");
        cakePicture.setcId(1);
        if(dao.updateCakePictureById(cakePicture)>0){
            System.out.println("修改成功！");
        }
    }
    //删除图片
    @Test
    public void deleteCakePictureById(){
        CakePicture cakePicture=new CakePicture();
        cakePicture.setCpPicture("1.jpg");
        cakePicture.setcId(1);
        if(dao.deleteCakePictureById(cakePicture)>0){
            System.out.println("删除成功！");
        }
    }
}
