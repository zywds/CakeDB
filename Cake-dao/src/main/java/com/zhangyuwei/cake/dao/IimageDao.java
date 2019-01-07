package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.CakeInformation;
import com.zhangyuwei.cake.entities.CakePicture;

import java.util.List;

public interface IimageDao {
    //查询所有商品
    List<CakeInformation> selectCakeInformation();
    //查询图片表，根据商品id
    List<CakePicture> selectCakePicture(CakePicture cakePicture);
    //查询图片表中商品对应的图片数量
    int selectCakePictureCount(int cId);
    //上传图片单
    int insertCakePicture(CakePicture cakePicture);
    //上传图片多
    int insertCakePictureSome(List<CakePicture> cakePictureList);
    //修改图片
    int updateCakePictureById(CakePicture cakePicture);
    //删除图片
    int deleteCakePictureById(CakePicture cakePicture);
}
