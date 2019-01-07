package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.RegsistAdmin;
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
public class cakeDaoTest {
    @Autowired
    IcakeDao dao;
    //查询所有管理员
    @Test
    public void selectAllRegsistAdmin(){
        System.out.println(dao.selectAllRegsistAdmin());
    }
    //登录(根据账号，密码查询管理员)
    @Test
    public void selectRegsistAdminByUserAndPassword(){
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        regsistAdmin.setRaName("admir");
        regsistAdmin.setRaPassword("123456");
        if(dao.selectRegsistAdminByUserAndPassword(regsistAdmin).size()==1){
            System.out.println(dao.selectRegsistAdminByUserAndPassword(regsistAdmin));
            System.out.println("管理员登录成功！");
        }
    }
    //查询，根据其权限进行查询
    @Test
    public void selectRegsistAdminByRole(){
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        regsistAdmin.setRaName("Admirs");
        regsistAdmin.setRaRole("普通管理员");
        System.out.println(dao.selectRegsistAdminByRole(regsistAdmin));
    }
    //查询，分页
    @Test
    public void selectAdminByPage(){
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        Map<String,Object> objectMap=new HashMap<String,Object>();
        objectMap.put("raName","Admirs");
        objectMap.put("raRole","普通管理员");
        objectMap.put("page",0);
        objectMap.put("limit",4);
        System.out.println(dao.selectAdminByPage(objectMap));
    }
    //查询数量
    @Test
    public void selectAdminCount(){
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        regsistAdmin.setRaName("A");
        int count=dao.selectAdminCount(regsistAdmin);
        System.out.println(count);
    }
    //注销
    @Test
    public void updateState(){
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        regsistAdmin.setRaId(2);
        regsistAdmin.setRaState(0);
        if(dao.updateState(regsistAdmin)>0){
            System.out.println("修改成功！");
        }
    }
    //添加管理员
    @Test
    public void insertRegsistAdmin(){
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        regsistAdmin.setRaName("addAdmir1");regsistAdmin.setRaPhone("15654565433");
        regsistAdmin.setRaEmail("344545676@qq.com");regsistAdmin.setRaPassword("343434");
        regsistAdmin.setRaRole("普通管理员");
        if(dao.insertRegsistAdmin(regsistAdmin)>0){
            System.out.println("添加成功！");
        }
    }
    //修改管理员
    @Test
    public void updateRegsistAdmin(){
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        regsistAdmin.setRaName("Admir");regsistAdmin.setRaPhone("12332343439");
        regsistAdmin.setRaEmail("736375819@qq.com");regsistAdmin.setRaRole("超级管理员");
        regsistAdmin.setRaId(1);
        if(dao.updateRegsistAdmin(regsistAdmin)>0){
            System.out.println("修改成功！");
        }
    }
    //多项重置密码
    @Test
    public void updateRegsistAdminPassword(){
        List<Integer> raIds=new ArrayList<Integer>();
        raIds.add(7);raIds.add(111);
        if(dao.updateRegsistAdminPassword(raIds)==raIds.size()){
            System.out.println("重置成功！");
        }
    }
    //判断登录名是否被注册
    @Test
    public void selectAdminByNameIfRegsist(){
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        regsistAdmin.setRaName("zywds");
        if(dao.selectAdminByNameIfRegsist(regsistAdmin)==0){
            System.out.println("注册成功!");
        }else{
            System.out.println("注册失败!");
        }
    }
}
