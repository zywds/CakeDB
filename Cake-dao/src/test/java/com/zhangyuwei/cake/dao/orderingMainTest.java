package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class orderingMainTest {
    @Autowired
    Iordering dao;
    //生成一条订单
    @Test
    public void insertOrding(){
        //oPTime,oAction,oDesc,oName,oPhone,oAddress,rId
        Ording ording=new Ording();
        Date oPTime=new Date();
        ording.setoPTime(oPTime);ording.setoAction("自提");ording.setoDesc("甜度");ording.setoName("laozhang");
        ording.setoPhone("14565454323");ording.setoAddress("都昌三中");ording.setrId(1);
        if(dao.insertOrding(ording)>0){
            System.out.println("添加成功！");//返回订单号
        }
    }
    //一条订单对应多条数据
    @Test
    public void insertOrdingDesc(){
        //odPrice,odPoundage,odWareTable,odSumPrice,odNumber,oId,cId
        List<OrderingDesc> orderingDescList=new ArrayList<OrderingDesc>();
        BigDecimal odPrice=new BigDecimal("34");
        orderingDescList.add(new OrderingDesc(odPrice,1,3,odPrice,2,1,1));
        orderingDescList.add(new OrderingDesc(odPrice,1,3,odPrice,2,1,2));
        if(dao.insertOrdingDesc(orderingDescList)==2){
            System.out.println("添加成功！");
        }
    }
    //查询新增的订单号
    @Test
    public void selectOrdingAddOId(){
        System.out.println(dao.selectOrdingAddOId());
    }
    //修改状态为已完成
    @Test
    public void updateOrderingStateSuccess(){
        if(dao.updateOrderingStateSuccess(2)>0){
            System.out.println("已完成");
        }
    }
    //修改状态为已取消
    @Test
    public void updateOrderingStateCancel(){
        if(dao.updateOrderingStateCancel(2)>0){
            System.out.println("已取消");
        }
    }
    //根据订单号查询订单状态
    @Test
    public void selectOrderingOstateByOId(){
        System.out.println(dao.selectOrderingOstateByOId(1));
    }
    //查询所有的订单
    @Test
    public void selectAllOrding(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("page",0);map.put("limit",10);map.put("oNo","");
        map.put("ostate","0");map.put("oAstate","");map.put("oPayAction","");
        System.out.println(dao.selectAllOrding(map));
    }
    //查询一个订单中的所有商品
    @Test
    public void selectAllOrdingDescByrId(){
        System.out.println(dao.selectAllOrdingDescByrId(1));
    }

    //根据oId查出订单号
    @Test
    public void selectONoByOId(){
        System.out.println(dao.selectONoByOId(1));
    }

    //修改支付方式
    @Test
    public void updatePayAction(){
        Ording ording=new Ording();
        ording.setoId(1);ording.setoPayAction("支付宝");
        System.out.println(dao.updatePayAction(ording));
        if(dao.updatePayAction(ording)>0){
            System.out.println("修改成功！");
        }
    }
    //根据用户改变购物车中的状态
    @Test
    public void updateShoppingState(){
        if(dao.updateShoppingState(1)>0){
            System.out.println("修改成功！");
        }
    }
    //查询订单详细表
    @Test
    public void selectOrderingDescAll(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rId",1);map.put("oState",0);
        System.out.println(dao.selectOrderingDescAll(map));
    }
    //查询所有的订单,根据用户编号，和订单状态
    @Test
    public void selectOrderingAllByRId(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rId",1);map.put("oState",0);map.put("page",0);map.put("limit",100000);
        System.out.println(dao.selectOrderingAllByRId(map));
    }
    //查询订单详细表，更据订单号
    @Test
    public void selectOrderingDescByOId(){
        System.out.println(dao.selectOrderingDescByOId(1));
    }
    //根据用户编号，和订单状态查询数量
    @Test
    public void selectOrdingCount(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rId",1);map.put("oState",0);
        System.out.println(dao.selectOrdingCount(map));
    }
    //根据订单编号修改地址
    @Test
    public void updateAddressByoId(){
        Ording ording=new Ording();
        ording.setoName("dd");ording.setoPhone("111");ording.setoAddress("dd");ording.setrId(1);
        if(dao.updateAddressByoId(ording)>0){
            System.out.println("修改成功1");
        }
    }
    //根据订单编号改变订单状态2
    @Test
    public void updateOrdingStateByOId(){
        if (dao.updateOrdingStateByOId(1)>0) {
            System.out.println("修改成功！");
        }
    }
    //根据订单编号改变订单状态3
    @Test
    public void updateOrdingStateByOId2(){
        if (dao.updateOrdingStateByOId2(1)>0) {
            System.out.println("修改成功！");
        }
    }
    //取消重订后添加商品到购物车(多添加)
    @Test
    public void insertShoppingFromOrderingDesc(){
        List<Shopping> shoppingList=new ArrayList<Shopping>();
        BigDecimal bigDecimal=new BigDecimal("34");
        shoppingList.add(new Shopping(1,bigDecimal,1,1,bigDecimal,1,"",1));
        shoppingList.add(new Shopping(2,bigDecimal,1,1,bigDecimal,1,"",1));
        if(dao.insertShoppingFromOrderingDesc(shoppingList)==2){
            System.out.println("添加成功！");
        }

    }
    //查询出所有订单的数量
    @Test
    public void selectAllOrderingCount(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("oNo","1545481072681");
        map.put("ostate","");map.put("oAstate","");map.put("oPayAction","");
        System.out.println(dao.selectAllOrderingCount(map));
    }
    //根据管理员改变的状态改变
    @Test
    public void updateOrderingoAstateByOid(){
        Ording ording=new Ording();
        ording.setoId(1);ording.setoAstate(1);
        if(dao.updateOrderingoAstateByOid(ording)>0){
            System.out.println("修改成功！");
        }
    }
    //查询一个订单中的所有商品分页
    @Test
    public void selectAllOrdingDescByrIdPage(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("oId",1);map.put("page",0);map.put("limit",4);
        System.out.println(dao.selectAllOrdingDescByrIdPage(map));
    }
    //查询一个订单中的所有商品分页数量
    @Test
    public void selectAllOrdingDescByrIdPageCount(){
        System.out.println(dao.selectAllOrdingDescByrIdPageCount(1));
    }
    //查询总价
    @Test
    public void selectOrderingDescSumPrice(){
        System.out.println(dao.selectOrderingDescSumPrice(1));
    }
}
