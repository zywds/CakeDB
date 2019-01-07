package com.zhangyuwei.cake.controller;

import com.sun.deploy.net.HttpResponse;
import com.zhangyuwei.cake.dao.IpricePoundageDao;
import com.zhangyuwei.cake.entities.CakeInformation;
import com.zhangyuwei.cake.entities.PricePoundage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("X-admin/PricePoundageServlet")
public class pricePoundageServlet {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    IpricePoundageDao dao=ctx.getBean(IpricePoundageDao.class);
    //查询所有商品(展示在下拉框，便于选择所对应的商品)
    @RequestMapping(value = "/selectCakeInformationAll",method = RequestMethod.POST)
    @ResponseBody
    public List<CakeInformation> selectCakeInformationAll(){
        return dao.selectCakeInformationAll();
    }
    //查询商品所对应的价格磅数
    @RequestMapping(value = "/selectPricePoundageById",method = RequestMethod.POST)
    @ResponseBody
    public List<PricePoundage> selectPricePoundageById(@RequestBody List<Object> objectList,HttpServletRequest request){
        Object cId=objectList.get(2);
        Object page=objectList.get(0);Object limit=objectList.get(1);
        int pages=(int)page;int limits=(int)limit;
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("page",(pages-1)*limits);
        map.put("limit",limits);
        //判断当前商品页Id页是否为空，当然在登录时要设置其值为空，否则找不到报500，
        if(!request.getSession().getAttribute("pricePoundageCids").equals("")){
            cId=request.getSession().getAttribute("pricePoundageCids");
            //取得值之后记得重新设置为空
            request.getSession().setAttribute("pricePoundageCids","");
        }
        request.getSession().setAttribute("cakePictureCids","");
        map.put("cId",cId);
        System.out.println(dao.selectPricePoundageById(map));
        return dao.selectPricePoundageById(map);
    }
    //查询商品所对应的价格磅数数量
    @RequestMapping(value = "/selectPricePoundageByIdCount",method = RequestMethod.POST)
    @ResponseBody
    public int selectPricePoundageByIdCount(HttpServletRequest request){
        int cId=Integer.parseInt(request.getParameter("cId"));
        //判断当前商品页Id页是否为空，当然在登录时要设置其值为空，否则找不到报500，
        if(!request.getSession().getAttribute("pricePoundageCids").equals("")){
            cId=(int)request.getSession().getAttribute("pricePoundageCids");
            //取得值之后记得重新设置为空
            //request.getSession().setAttribute("pricePoundageCids","");
        }
        //request.getSession().setAttribute("cakePictureCids","");
        System.out.println("------------");
        System.out.println(cId);
        System.out.println("-------------");
        return dao.selectPricePoundageByIdCount(cId);
    }
    //添加价格与磅数
    @RequestMapping(value = "/insertPricePoundage",method = RequestMethod.POST)
    @ResponseBody
    public void insertPricePoundage(@RequestBody List<PricePoundage> pricePoundageList, HttpServletResponse response,HttpServletRequest request) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        //价格（Bigdecimal）ppPrice
        BigDecimal ppPrice=pricePoundageList.get(0).getPpPrice();
        //磅数(Bigdecimal)ppPoundage
        BigDecimal ppPoundage=pricePoundageList.get(0).getPpPoundage();
        //ppPicture string
        String ppPicture=pricePoundageList.get(0).getPpPicture();
        //ppSizeLeft int
        int ppSizeLeft=pricePoundageList.get(0).getPpSizeLeft();
        //ppSizeRight int
        int ppSizeRight=pricePoundageList.get(0).getPpSizeRight();
        //ppMinPeople int
        int ppMinPeople=pricePoundageList.get(0).getPpMinPeople();
        //ppMaxPeople int
        int ppMaxPeople=pricePoundageList.get(0).getPpMaxPeople();
        //ppTableWare int
        int ppTableWare=pricePoundageList.get(0).getPpTableWare();
        //ppTime(String)
        String ppTime=pricePoundageList.get(0).getPpTime();
        //cId
        int cId=pricePoundageList.get(0).getcId();
        PricePoundage pricePoundage=new PricePoundage();
        pricePoundage.setPpPrice(ppPrice);pricePoundage.setPpPoundage(ppPoundage);
        pricePoundage.setPpPicture(ppPicture);pricePoundage.setPpSizeLeft(ppSizeLeft);
        pricePoundage.setPpSizeRight(ppSizeRight);pricePoundage.setPpMinPeople(ppMinPeople);
        pricePoundage.setPpMaxPeople(ppMaxPeople);pricePoundage.setPpTableWare(ppTableWare);
        pricePoundage.setPpTime(ppTime);
        pricePoundage.setcId(cId);
        if(dao.insertPricePoundage(pricePoundage)>0){
            //设置sessionName值(设置修改后的ID值，停留在当前商品的图片页)
            HttpSession session=request.getSession();
            session.setAttribute("pricePoundageCids",cId);
            response.getWriter().print("添加成功!");
        }else{
            response.getWriter().print("添加失败!");
        }
    }
    //修改价格磅数表，根据价格磅数id和磅数(磅数唯一)
    @RequestMapping(value = "/updatePricePoundageByIdAndPoundage",method = RequestMethod.POST)
    @ResponseBody
    public void updatePricePoundageByIdAndPoundage(@RequestBody List<PricePoundage> pricePoundageList, HttpServletResponse response,HttpServletRequest request) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        //价格（Bigdecimal）ppPrice
        BigDecimal ppPrice=pricePoundageList.get(0).getPpPrice();
        //磅数(Bigdecimal)ppPoundage
        BigDecimal ppPoundage=pricePoundageList.get(0).getPpPoundage();
        //ppPicture string
        String ppPicture=pricePoundageList.get(0).getPpPicture();
        //ppSizeLeft int
        int ppSizeLeft=pricePoundageList.get(0).getPpSizeLeft();
        //ppSizeRight int
        int ppSizeRight=pricePoundageList.get(0).getPpSizeRight();
        //ppMinPeople int
        int ppMinPeople=pricePoundageList.get(0).getPpMinPeople();
        //ppMaxPeople int
        int ppMaxPeople=pricePoundageList.get(0).getPpMaxPeople();
        //ppTableWare int
        int ppTableWare=pricePoundageList.get(0).getPpTableWare();
        //ppTime(String)
        String ppTime=pricePoundageList.get(0).getPpTime();
        //cId
        int cId=pricePoundageList.get(0).getcId();
        int ppId=pricePoundageList.get(0).getPpId();
        PricePoundage pricePoundage=new PricePoundage();
        pricePoundage.setPpPrice(ppPrice);pricePoundage.setPpPoundage(ppPoundage);
        pricePoundage.setPpPicture(ppPicture);pricePoundage.setPpSizeLeft(ppSizeLeft);
        pricePoundage.setPpSizeRight(ppSizeRight);pricePoundage.setPpMinPeople(ppMinPeople);
        pricePoundage.setPpMaxPeople(ppMaxPeople);pricePoundage.setPpTableWare(ppTableWare);
        pricePoundage.setPpTime(ppTime);
        pricePoundage.setcId(cId);pricePoundage.setPpId(ppId);
        if(dao.updatePricePoundageByIdAndPoundage(pricePoundage)>0){
            //设置sessionName值(设置修改后的ID值，停留在当前商品的图片页)
            HttpSession session=request.getSession();
            session.setAttribute("pricePoundageCids",cId);
            response.getWriter().print("修改成功!");
        }else{
            response.getWriter().print("修改失败!");
        }
    }
    @RequestMapping(value = "/deletePricePoundageByIdAndPoundage",method = RequestMethod.POST)
    @ResponseBody
    public void deletePricePoundageByIdAndPoundage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        int ppId=Integer.parseInt(request.getParameter("ppId"));
        if(dao.deletePricePoundageByIdAndPoundage(ppId)>0){
            response.getWriter().print("删除成功!");
        }else{
            response.getWriter().print("删除失败!");
        }
    }
}
