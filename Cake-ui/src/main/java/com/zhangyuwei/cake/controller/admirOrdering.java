package com.zhangyuwei.cake.controller;

import com.zhangyuwei.cake.dao.IcakeMainDao;
import com.zhangyuwei.cake.dao.Iordering;
import com.zhangyuwei.cake.entities.OrderingDesc;
import com.zhangyuwei.cake.entities.Ording;
import com.zhangyuwei.utils.SalesTicket2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("X-admin/AdmirOrdering")
public class admirOrdering {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    Iordering dao=ctx.getBean(Iordering.class);
    //查询所有的订单
    @RequestMapping(value = "/selectAllOrding",method = RequestMethod.POST)
    @ResponseBody
    public List<Ording> selectAllOrding(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        int page=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        int pages=(page-1)*limit;map.put("page",pages);map.put("limit",limit);
        String oNo=request.getParameter("oNo");
        map.put("oNo",oNo);
        String ostate=request.getParameter("ostate");
        String oAstate=request.getParameter("oAstate");
        String oPayAction=request.getParameter("oPayAction");
        map.put("ostate",ostate);map.put("oAstate",oAstate);map.put("oPayAction",oPayAction);
        return dao.selectAllOrding(map);
    }
    //查询出所有订单的数量
    @RequestMapping(value = "/selectAllOrderingCount",method = RequestMethod.POST)
    @ResponseBody
    public int selectAllOrderingCount(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        String oNo=request.getParameter("oNo");
        map.put("oNo",oNo);
        String ostate=request.getParameter("ostate");
        String oAstate=request.getParameter("oAstate");
        String oPayAction=request.getParameter("oPayAction");
        map.put("ostate",ostate);map.put("oA state",oAstate);map.put("oPayAction",oPayAction);
        return dao.selectAllOrderingCount(map);
    }
    //查询一个订单中的所有商品
    @RequestMapping(value = "/selectAllOrdingDescByrId",method = RequestMethod.POST)
    @ResponseBody
    public List<OrderingDesc> selectAllOrdingDescByrId(HttpServletRequest request){
        int oId=Integer.parseInt(request.getParameter("oId"));
        return dao.selectAllOrdingDescByrId(oId);
    }
    //根据管理员改变的状态改变
    @RequestMapping(value = "/updateOrderingoAstateByOid",method = RequestMethod.POST)
    @ResponseBody
    public void updateOrderingoAstateByOid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        int oId=Integer.parseInt(request.getParameter("oId"));
        int oAstate=Integer.parseInt(request.getParameter("oAstate"));
        Ording ording=new Ording();
        ording.setoId(oId);ording.setoAstate(oAstate);
        if(dao.updateOrderingoAstateByOid(ording)>0){
            response.getWriter().print("修改成功！");
        }else{
            response.getWriter().print("修改失败！");
        }

    }
    //查询一个订单中的所有商品分页
    @RequestMapping(value = "/selectAllOrdingDescByrIdPage",method = RequestMethod.POST)
    @ResponseBody
    public List<OrderingDesc> selectAllOrdingDescByrIdPage(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        int oId=Integer.parseInt(request.getParameter("oId"));
        int page=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        int pages=(page-1)*limit;
        map.put("oId",oId);map.put("page",pages);map.put("limit",limit);
        return dao.selectAllOrdingDescByrIdPage(map);
    }
    //查询一个订单中的所有商品分页数量
    @RequestMapping(value = "/selectAllOrdingDescByrIdPageCount",method = RequestMethod.POST)
    @ResponseBody
    public int selectAllOrdingDescByrIdPageCount(HttpServletRequest request){
        int oId=Integer.parseInt(request.getParameter("oId"));
        return dao.selectAllOrdingDescByrIdPageCount(oId);
    }
    //查询总价
    @RequestMapping(value = "/selectOrderingDescSumPrice",method = RequestMethod.POST)
    @ResponseBody
    public String selectOrderingDescSumPrice(HttpServletRequest request){
        int oId=Integer.parseInt(request.getParameter("oId"));
        return dao.selectOrderingDescSumPrice(oId);
    }
    //打印订单
    @RequestMapping(value = "/pringOrdering",method = RequestMethod.POST)
    @ResponseBody
    public void pringOrdering(HttpServletRequest request,HttpServletResponse response){
        //查询出所有的订单包含所有的信息
        int oId=Integer.parseInt(request.getParameter("oId"));
        //获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat pageFormat = job.defaultPage();//得到默认页格式
        ArrayList<OrderingDesc> arrayList= (ArrayList<OrderingDesc>) dao.selectAllOrdingDescByrId(oId);
        int sale_num=0;// 商品总数
        BigDecimal sale_sum=new BigDecimal(dao.selectOrderingDescSumPrice(oId));// 总金额
        for(int i=0;i<arrayList.size();i++){
            sale_num+=arrayList.get(i).getOdNumber();
        }
        String cashier=(String)request.getSession().getAttribute("sessionName");//收银员编号
        BigDecimal practical=new BigDecimal(dao.selectOrderingDescSumPrice(oId));//实收
        BigDecimal changes=new BigDecimal("0");//找零
        String orders=arrayList.get(0).getOrding().getoNo();//订单号
        String rName=arrayList.get(0).getOrding().getoName();//客户姓名
        String rPhone=arrayList.get(0).getOrding().getoPhone();//电话号码
        String rAddress=arrayList.get(0).getOrding().getoAddress();//客户地址
        Date oPTime=arrayList.get(0).getOrding().getoPTime();//配送时间
        job.setPrintable(new SalesTicket2(arrayList,cashier,orders,sale_num,sale_sum,practical,changes,
                rName,rPhone,rAddress,oPTime));//设置打印类
        try {
            //可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
            boolean a=job.printDialog();
            if(a)
            {
                job.print();
            } else{
                job.cancel();
            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}
