package com.zhangyuwei.cake.controller;

import com.zhangyuwei.cake.commons.util.Person;
import com.zhangyuwei.cake.dao.IadmirOrderingLook;
import com.zhangyuwei.cake.dao.Iordering;
import com.zhangyuwei.cake.entities.AdmirLook;
import com.zhangyuwei.cake.entities.AdmirLookSumMoney;
import com.zhangyuwei.cake.entities.OrderingDesc;
import com.zhangyuwei.cake.entities.Ording;
import com.zhangyuwei.utils.SalesTicket2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
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
@RequestMapping("X-admin/AdmirOrderingLook")
public class admirOrderingLook {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    IadmirOrderingLook dao=ctx.getBean(IadmirOrderingLook.class);
    //查询所有商品信息各项数据的集合
    @RequestMapping(value = "/selectAdmirOrderingLook",method = RequestMethod.GET)
    @ResponseBody
    public AdmirLook selectAdmirOrderingLook(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map=new HashMap<String,Object>();
        String year=request.getParameter("year");
        String month=request.getParameter("month");
        String day=request.getParameter("day");
        map.put("year",year);map.put("month",month);map.put("day",day);
        //商品数量
        int sumNumber=dao.selectSumNumber();
        //所售商品数量
        int sallNumber=0;
        //销售最多商品
        String maxName="";
        List<OrderingDesc> orderingDescListMaxName=dao.selectMaxName(map);
        for(int i=0;i<orderingDescListMaxName.size();i++){
            if(i<orderingDescListMaxName.size()-1) {
                maxName += orderingDescListMaxName.get(i).getCakeInformation().getcName();
            }else{
                maxName += orderingDescListMaxName.get(i).getCakeInformation().getcName() + ",";
            }
        }
        //销售最少商品
        String minName="";
        List<OrderingDesc> orderingDescListMinName=dao.selectMinName(map);
        for(int i=0;i<orderingDescListMinName.size();i++){
            if(i<orderingDescListMaxName.size()-1) {
                minName += orderingDescListMinName.get(i).getCakeInformation().getcName();
            }else{
                minName += orderingDescListMinName.get(i).getCakeInformation().getcName() + ",";
            }
        }
        //管理员
        String admirName=(String)request.getSession().getAttribute("sessionName");
        //总销售额
        BigDecimal sumSallPrice=new BigDecimal("0");
        //获得所有商品完成订单总数
        List<OrderingDesc> orderingDescList=dao.selectAllOrderingDesc(map);
        for (int i=0;i<orderingDescList.size();i++){
            sallNumber+=orderingDescList.get(i).getOdNumber();
            sumSallPrice=sumSallPrice.add(orderingDescList.get(i).getOdSumPrice());
        }
        AdmirLook admirLook=new AdmirLook();
        admirLook.setSumNumber(sumNumber);
        admirLook.setSallNumber(sallNumber);
        admirLook.setSumSallPrice(sumSallPrice);
        admirLook.setMaxName(maxName);
        admirLook.setMinName(minName);
        admirLook.setAdmirName(admirName);
        return admirLook;
    }
    //查询所有销售商品的总价和数量
    @RequestMapping(value = "/selectOrderingDescSumPriceAndNumber",method = RequestMethod.GET)
    @ResponseBody
    public List<AdmirLookSumMoney> selectOrderingDescSumPriceAndNumber(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        String year=request.getParameter("year");
        String month=request.getParameter("month");
        String day=request.getParameter("day");
        map.put("year",year);map.put("month",month);map.put("day",day);
        List<OrderingDesc> orderingDescList=dao.selectOrderingDescSumPriceAndNumber(map);
        int[] cId=new int[orderingDescList.size()];
        for(int i=0;i<orderingDescList.size();i++){
            cId[i]=orderingDescList.get(i).getcId();
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<cId.length; i++) {
            if(!list.contains(cId[i])) {
                list.add(cId[i]);
            }
        }
        List<AdmirLookSumMoney> lists2=new ArrayList<AdmirLookSumMoney>();
        for(int j=0;j<list.size();j++){
            AdmirLookSumMoney lists=new AdmirLookSumMoney();
            int number=0;
            BigDecimal bigDecimal= new BigDecimal(0);
            String picture="";
            String name="";
            for(int i=0;i<orderingDescList.size();i++){
                if(orderingDescList.get(i).getcId()==list.get(j)){
                    number+=orderingDescList.get(i).getOdNumber();
                    bigDecimal=bigDecimal.add(orderingDescList.get(i).getOdSumPrice());
                    picture=orderingDescList.get(i).getCakeInformation().getcPicture();
                    name=orderingDescList.get(i).getCakeInformation().getcName();
                }
            }
            lists.setNumber(number);
            lists.setPicture(picture);
            lists.setSumPrice(bigDecimal);
            lists.setName(name);
            lists2.add(lists);
        }
        //排序，正序
        String sortId= request.getParameter("sortId");
        if(sortId.equals("1")) {
            Collections.sort(lists2, new Comparator<AdmirLookSumMoney>() {
                /*
                 * int compare(Person p1, Person p2) 返回一个基本类型的整型，
                 * 返回负数表示：p1 小于p2，
                 * 返回0 表示：p1和p2相等，
                 * 返回正数表示：p1大于p2
                 */
                public int compare(AdmirLookSumMoney y, AdmirLookSumMoney e) {
                    //按照Person的年龄进行升序排列
                    if (y.getNumber() > e.getNumber()) {
                        return 1;
                    }
                    if (y.getNumber() == e.getNumber()) {
                        return 0;
                    }
                    return -1;
                }
            });
        }else if(sortId.equals("2")){
            //排序，倒序
            Collections.sort(lists2, new Comparator<AdmirLookSumMoney>() {
                /*
                 * int compare(Person p1, Person p2) 返回一个基本类型的整型，
                 * 返回负数表示：p1 小于p2，
                 * 返回0 表示：p1和p2相等，
                 * 返回正数表示：p1大于p2
                 */
                public int compare(AdmirLookSumMoney y, AdmirLookSumMoney e) {
                    //按照Person的年龄进行升序排列
                    if (y.getNumber() > e.getNumber()) {
                        return -1;
                    }
                    if (y.getNumber() == e.getNumber()) {
                        return 0;
                    }
                    return 1;
                }
            });
        }

        return lists2;
    }
    //查询所售商品更具年份和月份，查出所售商品数量
    @RequestMapping(value = "/selectAllOrderingDescByYearAndMonth",method = RequestMethod.GET)
    @ResponseBody
    public List<AdmirLookSumMoney> selectAllOrderingDescByYearAndMonth(HttpServletRequest request,HttpServletResponse response){
        List<AdmirLookSumMoney> admirLookSumMoney=new ArrayList<AdmirLookSumMoney>();
        String year=request.getParameter("year");
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("year",year);
        for (int i=0;i<12;i++){
            BigDecimal bigDecimal=new BigDecimal(0);
            int number=0;
            map.put("month",i+1);
            if(dao.selectAllOrderingDescByYearAndMonth(map).size()>0){
                for (int j=0;j<dao.selectAllOrderingDescByYearAndMonth(map).size();j++) {
                    bigDecimal = bigDecimal.add(dao.selectAllOrderingDescByYearAndMonth(map).get(j).getOdSumPrice());
                    number += dao.selectAllOrderingDescByYearAndMonth(map).get(j).getOdNumber();
                }
            }
            admirLookSumMoney.add(new AdmirLookSumMoney(number,bigDecimal));
        }
        return admirLookSumMoney;
    }
}
