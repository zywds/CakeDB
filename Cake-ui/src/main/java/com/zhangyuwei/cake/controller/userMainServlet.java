package com.zhangyuwei.cake.controller;

import com.zhangyuwei.cake.dao.IcakeMainDao;
import com.zhangyuwei.cake.dao.IuserMainDao;
import com.zhangyuwei.cake.entities.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cake/CakeMainServlet")
public class userMainServlet {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    IuserMainDao dao=ctx.getBean(IuserMainDao.class);

    //查询蛋糕有关的一切信息(多对多)
    @RequestMapping(value = "/selectCakeInformationAll",method = RequestMethod.POST)
    @ResponseBody
    public List<SmallTypeInformation> selectCakeInformationAll(HttpServletRequest request){
        String stName=request.getParameter("stName");
        return dao.selectCakeInformationAll(stName);
    }
    //查询所有商品（新品）
    @RequestMapping(value = "/selectCakeInformation",method = RequestMethod.POST)
    @ResponseBody
    public List<CakeInformation> selectCakeInformation(){
        return dao.selectCakeInformation();
    }
    //根据id查询出商品
    @RequestMapping(value = "/selectCakeInformationByCId",method = RequestMethod.POST)
    @ResponseBody
    public List<CakeInformation> selectCakeInformationByCId(HttpServletRequest request){
        int cId=Integer.parseInt(request.getParameter("cId"));
        return dao.selectCakeInformationByCId(cId);
    }
    //根据商品id查出小类型
    @RequestMapping(value = "/selectSmallTypeformationByCId",method = RequestMethod.POST)
    @ResponseBody
    public List<SmallTypeInformation> selectSmallTypeformationByCId(HttpServletRequest request){
        int cId=Integer.parseInt(request.getParameter("cId"));
        return dao.selectSmallTypeformationByCId(cId);
    }
    //根据id查出磅数
    @RequestMapping(value = "/selectPricePoundageByCId",method = RequestMethod.POST)
    @ResponseBody
    public List<PricePoundage> selectPricePoundageByCId(HttpServletRequest request){
        int cId=Integer.parseInt(request.getParameter("cId"));
        return dao.selectPricePoundageByCId(cId);
    }
    //根据id查出图片
    @RequestMapping(value = "/selectCakepicture",method = RequestMethod.POST)
    @ResponseBody
    public List<CakePicture> selectCakepicture(HttpServletRequest request){
        int cId=Integer.parseInt(request.getParameter("cId"));
        System.out.println(cId);
        return dao.selectCakepicture(cId);
    }
    //查询商品口味和商品类型到下拉框
    @RequestMapping(value = "/selectCakeTypeAndMouseTypeToSelect",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectCakeTypeAndMouseTypeToSelect(){
        Map<String,Object> map=new HashMap<String,Object>();
        //查询商品类型表
        List<CakeType> cakeTypeList=dao.selectCakeInformationCakeType();
        //查询口味表
        List<MouseType> mouseTypeList=dao.selectCakeInformationMouseType();
        //查询蛋糕小类型表
        List<SmallCakeType> smallCakeTypes=dao.selectSmallCakeType();
        map.put("cakeType",cakeTypeList);map.put("mouseType",mouseTypeList);
        map.put("smallCakeType",smallCakeTypes);
        return map;
    }
    //查询出所有商品，根据类型和口味
    @RequestMapping(value = "/selectCakeInformationByctIdAndmtId",method = RequestMethod.POST)
    @ResponseBody
    public List<CakeInformation> selectCakeInformationByctIdAndmtId(@RequestBody List<String> stringList){
        Map<String,String> map=new HashMap<String,String>();
        map.put("ctId",stringList.get(0));map.put("mtId",stringList.get(1));
        return dao.selectCakeInformationByctIdAndmtId(map);
    }

}
