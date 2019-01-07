package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.Regsist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class userDaoTest {
    @Autowired
    IuserDao dao;
    //查询用户以及新增用户
    @Test
    public void selectRegsistUserInformation(){
        System.out.println(dao.selectRegsistUserInformation(1));
    }
    //查询所有用户
    @Test
    public void selectRegsist(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rPhone","12454546543");map.put("page",0);map.put("limit",10);
        System.out.println(dao.selectRegsist(map));
    }
    //查询所有用户数量
    @Test
    public void selectRegsistCount(){
        Regsist regsist=new Regsist();
        regsist.setrPhone("12454546543");
        System.out.println(dao.selectRegsistCount(regsist));
    }
}
