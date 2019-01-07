package com.zhangyuwei.cake.controller;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.zhangyuwei.cake.dao.IcakeMainDao;
import com.zhangyuwei.cake.dao.Iordering;
import com.zhangyuwei.cake.entities.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("cake/OrderingServlet")
public class orderingServlet {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    Iordering dao=ctx.getBean(Iordering.class);
    //一条订单对应多条数据
    @RequestMapping(value = "/insertOrdingDesc",method = RequestMethod.POST)
    @ResponseBody
    public void insertOrdingDesc(@RequestBody List<OrderingDesc> ordingList,HttpServletResponse response,HttpServletRequest request) throws IOException {
        response.setCharacterEncoding("utf-8");
        List<OrderingDesc> orderingDescList=new ArrayList<OrderingDesc>();
        for(int i=0;i<ordingList.size();i++) {
            orderingDescList.add(new OrderingDesc(ordingList.get(i).getOdPrice(), ordingList.get(i).getOdPoundage(), ordingList.get(i).getOdTableWare(), ordingList.get(i).getOdSumPrice(), ordingList.get(i).getOdNumber(), ordingList.get(i).getoId(), ordingList.get(i).getcId()));
        }
        System.out.println(orderingDescList);
        if(dao.insertOrdingDesc(orderingDescList)==ordingList.size()){
            //获得session中的值
            HttpSession session=request.getSession();
            int rId=(int)session.getAttribute("rId");
            dao.updateShoppingState(rId);
            response.getWriter().print("完成订单!");
        }else{
            response.getWriter().print("完成订单失败!");
        }
    }
    //生成一条订单
    @RequestMapping(value = "/insertOrding",method = RequestMethod.POST)
    @ResponseBody
    public int insertOrding(@RequestBody List<Ording> ordingList,HttpServletRequest request){
        System.out.println(ordingList);
        int oId=0;
        Ording ording=new Ording();
        //当前时间作为时间戳
        Date oTime=new Date();
        long ts = oTime.getTime();
        String oNo = String.valueOf(ts);
        ording.setoNo(oNo);//时间戳
        ording.setoPTime(ordingList.get(0).getoPTime());
        ording.setoAction(ordingList.get(0).getoAction());
        ording.setoDesc(ordingList.get(0).getoDesc());
        ording.setoName(ordingList.get(0).getoName());
        ording.setoPhone(ordingList.get(0).getoPhone());
        //获得session中的值
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        ording.setoAddress(ordingList.get(0).getoAddress());ording.setrId(rId);
        if(dao.insertOrding(ording)>0){
            oId=dao.selectOrdingAddOId();
        }
        return oId;
    }
    //根据oId查出订单号
    @RequestMapping(value = "/selectONoByOId",method = RequestMethod.POST)
    @ResponseBody
    public List<Ording> selectONoByOId(HttpServletRequest request){
        int oId=Integer.parseInt(request.getParameter("oId"));
        return dao.selectONoByOId(oId);
    }
    //修改支付方式
    @RequestMapping(value = "/updatePayAction",method = RequestMethod.POST)
    @ResponseBody
    public void updatePayAction(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        int oId=Integer.parseInt(request.getParameter("oId"));
        String oPayAction=request.getParameter("oPayAction");
        Ording ording=new Ording();
        ording.setoId(oId);ording.setoPayAction(oPayAction);
        if(dao.updatePayAction(ording)>0){
            response.getWriter().print("修改成功！");
        }else{
            response.getWriter().print("修改失败！");
        }
    }
    //根据用户改变购物车中的状态
    @RequestMapping(value = "/updateShoppingState",method = RequestMethod.POST)
    @ResponseBody
    public void updateShoppingState(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获得session中的值
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        if(dao.updateShoppingState(rId)>0){
            response.getWriter().print("修改成功！");
        }else{
            response.getWriter().print("修改失败！");
        }
    }
    //查询订单详细表
    @RequestMapping(value = "/selectOrderingDescAll",method = RequestMethod.POST)
    @ResponseBody
    public List<OrderingDesc> selectOrderingDescAll(HttpServletRequest request,HttpServletResponse response){
        //获得session中的值
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        int oState=Integer.parseInt(request.getParameter("oState"));
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rId",rId);map.put("oState",oState);
        return dao.selectOrderingDescAll(map);
    }
    //查询所有的订单,根据用户编号，和订单状态
    @RequestMapping(value = "/selectOrderingAllByRId",method = RequestMethod.POST)
    @ResponseBody
    public List<Ording> selectOrderingAllByRId(HttpServletRequest request,HttpServletResponse response){
        //获得session中的值
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        /*大于30分钟改变其状态*/
        Map<String,Object> mapState=new HashMap<String, Object>();
        mapState.put("rId",rId);mapState.put("oState",0);mapState.put("page",0);mapState.put("limit",100000);
        List<Ording> ordingList=dao.selectOrderingAllByRId(mapState);
        Date str1=new Date();
        for (int i=0;i<ordingList.size();i++){
            long from = ordingList.get(i).getoTime().getTime();
            long to=str1.getTime();
            int minutes = (int) ((to - from)/(1000 * 60));
            if(minutes>=30){
                //改变用户订单状态
                int oId=ordingList.get(i).getoId();
                dao.updateOrdingStateByOId(oId);
            }
        }
        /*返回查询的订单*/
        int oState=Integer.parseInt(request.getParameter("oState"));
        int page=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        int pages=(page-1)*limit;
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rId",rId);map.put("oState",oState);map.put("page",pages);map.put("limit",limit);
        return dao.selectOrderingAllByRId(map);
    }
    //查询订单详细表，更据订单号
    @RequestMapping(value = "/selectOrderingDescByOId",method = RequestMethod.POST)
    @ResponseBody
    public List<OrderingDesc> selectOrderingDescByOId(HttpServletRequest request){
        int oId=Integer.parseInt(request.getParameter("oId"));
        return dao.selectOrderingDescByOId(oId);
    }
    //根据用户编号，和订单状态查询数量
    @RequestMapping(value = "/selectOrdingCount",method = RequestMethod.POST)
    @ResponseBody
    public int selectOrdingCount(HttpServletRequest request,HttpServletResponse response){
        //获得session中的值
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        int oState=Integer.parseInt(request.getParameter("oState"));
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rId",rId);map.put("oState",oState);
        return dao.selectOrdingCount(map);
    }
    //根据订单编号修改地址
    @RequestMapping(value = "/updateAddressByoId",method = RequestMethod.POST)
    @ResponseBody
    public void updateAddressByoId(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        Ording ording=new Ording();
        String oName=request.getParameter("oName");String oPhone=request.getParameter("oPhone");
        String oAddress=request.getParameter("oAddress");
        int oId=Integer.parseInt(request.getParameter("oId"));
        ording.setoName(oName);ording.setoPhone(oPhone);ording.setoAddress(oAddress);ording.setoId(oId);
        if(dao.updateAddressByoId(ording)>0){
            response.getWriter().print("修改成功!");
        }else{
            response.getWriter().print("修改失败！");
        }
    }
    //查询一个订单中的所有商品
    @RequestMapping(value = "/selectAllOrdingDescByrId",method = RequestMethod.POST)
    @ResponseBody
    public List<OrderingDesc> selectAllOrdingDescByrId(HttpServletRequest request){
        int oId=Integer.parseInt(request.getParameter("oId"));
        return dao.selectAllOrdingDescByrId(oId);
    }
    //根据订单编号改变订单状态已取消
    @RequestMapping(value = "/updateOrdingStateByOId",method = RequestMethod.POST)
    @ResponseBody
    public void updateOrdingStateByOId(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        int oId=Integer.parseInt(request.getParameter("oId"));
        if(dao.updateOrdingStateByOId(oId)>0){
            response.getWriter().write("取消成功！");
        }else{
            response.getWriter().write("取消失败！");
        }
    }
    //根据订单编号改变订单状态已完成
    @RequestMapping(value = "/updateOrdingStateByOId2",method = RequestMethod.POST)
    @ResponseBody
    public void updateOrdingStateByOId2(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        int oId=Integer.parseInt(request.getParameter("oId"));
        if(dao.updateOrdingStateByOId2(oId)>0){
            response.getWriter().write("支付完成！");
        }else{
            response.getWriter().write("支付失败！");
        }
    }
    //取消重订后添加商品到购物车(多添加)
    @RequestMapping(value = "/insertShoppingFromOrderingDesc",method = RequestMethod.POST)
    @ResponseBody
    public void insertShoppingFromOrderingDesc(@RequestBody List<Shopping> shoppingList,HttpServletResponse response,HttpServletRequest request) throws IOException {
        response.setCharacterEncoding("utf-8");
        //获得session中的值
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        List<Shopping> shoppingList1=new ArrayList<>();
        for (int i=0;i<shoppingList.size();i++){
            shoppingList1.add(new Shopping(shoppingList.get(i).getcId(),shoppingList.get(i).getsPrice(),shoppingList.get(i).getsPoundage(),shoppingList.get(i).getsNumber(),shoppingList.get(i).getsSumPrice(),1,"",rId));
        }
        if(dao.insertShoppingFromOrderingDesc(shoppingList1)==shoppingList.size()){
            //取消订单
            int oId=shoppingList.get(0).getsState();
            if(dao.updateOrdingStateByOId(oId)>0){
                response.getWriter().print("重新下单成功！");
            }else{
                response.getWriter().print("重新下单失败！");
            }
        }else{
            response.getWriter().print("重新下单失败！");
        }
    }
    //查询出订单时间做出判断
    @RequestMapping(value = "/selectOrderingoTime",method = RequestMethod.GET)
    @ResponseBody
    public void selectOrderingoTime(HttpServletResponse response,HttpServletRequest request) throws ParseException, IOException {
        response.setCharacterEncoding("utf-8");
        //        //获得session中的值
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rId",rId);map.put("oState",0);map.put("page",0);map.put("limit",100000);
        List<Ording> ordingList=dao.selectOrderingAllByRId(map);
        Date str1=new Date();
        for (int i=0;i<ordingList.size();i++){
            //Calendar c = Calendar.getInstance();
            //String str ="2018-12-23 17:00";
            //Date str1=new Date();
            //SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");//如2016-08-10 20:40
            long from = ordingList.get(i).getoTime().getTime();
            long to=str1.getTime();
            int minutes = (int) ((to - from)/(1000 * 60));
            if(minutes>=30){
                //改变用户订单状态
                int oId=ordingList.get(i).getoId();
                if(dao.updateOrdingStateByOId(oId)>0){
                    response.getWriter().print("修改成功！");
                }else{
                    response.getWriter().print("修改失败！");
                }
            }
        }
        response.getWriter().print("没有数据修改!");
    }
    //短信接口
    @RequestMapping(value = "/messageMouse",method = RequestMethod.GET)
    @ResponseBody
    public String messageMouse(HttpServletRequest request) throws HTTPException, IOException {
        // 短信应用SDK AppID
        int appid = 1400159819; // 1400开头
        // 短信应用SDK AppKey
        String appkey = "d399fed2be08d339d523d4e668f078af";
        // 需要发送短信的手机号码
        String rPhone=request.getParameter("rPhone");
        System.out.println(rPhone);
        String[] phoneNumbers = {rPhone};
        int templateId = 251945;
        String smsSign = "MUJI";
        // 指定模板ID单发短信
        /*
         * 1、验证码 2、参数。占位符
         * */
        String str="";
        Random random = new Random();
        for (int i = 0; i <6 ; i++) {
            str += random.nextInt(10);
        }
        String[] params = {str,"1"};
        SmsMultiSender msender = new SmsMultiSender(appid, appkey);
        SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
                templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
        System.out.print(result);
        return str;
    }

    //根据订单号查询订单状态(轮循)
    @RequestMapping(value = "/selectOrderingOstateByOId",method = RequestMethod.GET)
    @ResponseBody
    public int selectOrderingOstateByOId(HttpServletRequest request){
        //查询订单号
        int oId=Integer.parseInt(request.getParameter("oId"));
        return dao.selectOrderingOstateByOId(oId).get(0).getOstate();
    }
}
