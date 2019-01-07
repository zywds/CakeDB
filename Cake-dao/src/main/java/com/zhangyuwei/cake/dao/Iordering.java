package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.OrderingDesc;
import com.zhangyuwei.cake.entities.Ording;
import com.zhangyuwei.cake.entities.Shopping;

import java.util.List;
import java.util.Map;

public interface Iordering {
    //生成一条订单
    int insertOrding(Ording ording);
    //一条订单对应多条数据
    int insertOrdingDesc(List<OrderingDesc> orderingDescList);
    //查询新增的订单号
    int selectOrdingAddOId();
    //修改状态为已完成
    int updateOrderingStateSuccess(int oId);
    //修改状态为已取消
    int updateOrderingStateCancel(int oId);
    //查询所有的订单
    List<Ording> selectAllOrding(Map<String,Object> map);
    //查询一个订单中的所有商品
    List<OrderingDesc> selectAllOrdingDescByrId(int oId);
    //查询一个订单中的所有商品分页
    List<OrderingDesc> selectAllOrdingDescByrIdPage(Map<String,Object> map);
    //根据oId查出订单号
    List<Ording> selectONoByOId(int oId);
    //修改支付方式
    int updatePayAction(Ording ording);
    //根据用户改变购物车中的状态
    int updateShoppingState(int rId);
    //查询订单详细表
    List<OrderingDesc> selectOrderingDescAll(Map<String,Object> map);
    //查询所有的订单,根据用户编号，和订单状态
    List<Ording> selectOrderingAllByRId(Map<String,Object> map);
    //查询订单详细表，更据订单号
    List<OrderingDesc> selectOrderingDescByOId(int oId);
    //根据用户编号，和订单状态查询数量
    int selectOrdingCount(Map<String,Object> map);
    //根据订单编号修改地址
    int updateAddressByoId(Ording ording);
    //根据订单编号改变订单状态2
    int updateOrdingStateByOId(int oId);
    //据订单编号改变订单状态3
    int updateOrdingStateByOId2(int oId);
    //根据订单号查询订单状态
    List<Ording> selectOrderingOstateByOId(int oId);
    //根据订单号查询出订单详细表
    //List<OrderingDesc> selectOrderingDescByOId(int oId);
    //取消重订后添加商品到购物车(多添加)
    int insertShoppingFromOrderingDesc(List<Shopping> shoppingList);
    //查询出所有订单的数量
    int selectAllOrderingCount(Map<String,Object> map);
    //根据管理员改变的状态改变
    int updateOrderingoAstateByOid(Ording ording);
    //查询一个订单中的所有商品分页数量
    int selectAllOrdingDescByrIdPageCount(int oId);
    //查询总价
    String selectOrderingDescSumPrice(int oId);
}
