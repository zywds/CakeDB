package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.DatabaseBackup;
import com.zhangyuwei.cake.entities.HistoryShoppingDiary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class databaseBackupDaoTest {
    @Autowired
    IdatabaseBackupDao dao;
    //备份数据
    @Test
    public void insertdatabaseBackup(){
        DatabaseBackup databaseBackup=new DatabaseBackup();
        databaseBackup.setDpPath("dddd");databaseBackup.setDpFTime(new Date());
        if(dao.insertdatabaseBackup(databaseBackup)>0){
            System.out.println("备份成功！");
        }
    }
    //查询备份数据
    @Test
    public void selectdatabaseBackup(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("page",0);map.put("limit",5);
        System.out.println(dao.selectdatabaseBackup(map));
    }
    //查询数量
    @Test
    public void selectdatabaseCount(){
        System.out.println(dao.selectdatabaseCount());
    }
    //删除备份
    @Test
    public void deletedatabaseBackup(){
        int dpId=2;
        if(dao.deletedatabaseBackup(dpId)>0){
            System.out.println("删除成功！");
        }
    }
    //查询购物车表中状态为0的数据的总数
    @Test
    public void selectShoppingStateCount(){
        System.out.println(dao.selectShoppingStateCount());
    }
    //删除购物车中被迁移的数据
    @Test
    public void deleteShoppingSidIn(){
        if(dao.deleteShoppingSidIn()>0){
            System.out.println("删除成功！");
        }
    }
    //添加数据到迁移表
    @Test
    public void insertShoppingSidIn(){
        if(dao.insertShoppingSidIn()>0){
            System.out.println("迁移成功！");
        }
    }
    //添加数据到迁移记录表
    @Test
    public void insertHistoryShoppingDiary(){
        HistoryShoppingDiary historyShoppingDiary=new HistoryShoppingDiary();
        //获得迁移数量
        int htCount=dao.selectShoppingStateCount();
        //添加数据到迁移表中
        dao.insertShoppingSidIn();
        //删除迁移的数据
        dao.deleteShoppingSidIn();
        historyShoppingDiary.setHtCount(htCount);historyShoppingDiary.setHtState("迁移成功！");
        historyShoppingDiary.setHtAdmir("Admir");
        if(dao.insertHistoryShoppingDiary(historyShoppingDiary)>0){
            System.out.println("迁移成功！");
        }
    }
    //查询迁移记录表中的数据
    @Test
    public void selectHistoryShoppingDiary(){
        Map<String,Integer> map=new HashMap<String, Integer>();
        map.put("page",0);map.put("limit",5);
        System.out.println(dao.selectHistoryShoppingDiary(map));
    }
}
