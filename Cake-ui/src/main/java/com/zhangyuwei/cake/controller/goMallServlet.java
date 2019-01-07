package com.zhangyuwei.cake.controller;

import com.zhangyuwei.cake.dao.IcakeMainDao;
import com.zhangyuwei.cake.dao.IgoMall;
import com.zhangyuwei.cake.entities.*;
import jdk.nashorn.internal.ir.RuntimeNode;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/GoMallServlet")
public class goMallServlet {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    IgoMall dao=ctx.getBean(IgoMall.class);
    //查询所有的商品
    @RequestMapping(value = "/selectAllgoMall",method = RequestMethod.GET)
    @ResponseBody
    public List<GoMall> selectAllgoMall(){
        return dao.selectAllgoMall();
    }
    //删除商品根据编号
    @RequestMapping(value = "/deletegoMallById",method = RequestMethod.POST)
    @ResponseBody
    public void deletegoMallById(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        int gId=Integer.parseInt(request.getParameter("gId"));
        if(dao.deletegoMallById(gId)>0){
            response.getWriter().print("删除成功！");
        }else{
            response.getWriter().print("删除失败！");
        }
    }
    //添加商品
    @RequestMapping(value = "/insertgoMall",method = RequestMethod.POST)
    @ResponseBody
    public void insertgoMall(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        GoMall goMall=new GoMall();
        BigDecimal gPrice=new BigDecimal(request.getParameter("gPrice"));
        int gNumber=Integer.parseInt(request.getParameter("gNumber"));
        BigDecimal gSumPrice=gPrice.multiply(new BigDecimal(gNumber));
        goMall.setgName(request.getParameter("gName"));goMall.setgPrice(gPrice);
        goMall.setgNumber(gNumber);goMall.setgSumPrice(gSumPrice);
        if(dao.insertgoMall(goMall)>0){
            response.getWriter().print("添加成功！");
        }else{
            response.getWriter().print("添加失败！");
        }
    }
    //修改商品
    @RequestMapping(value = "/updategoMall",method = RequestMethod.POST)
    @ResponseBody
    public void updategoMall(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        GoMall goMall=new GoMall();
        BigDecimal gPrice=new BigDecimal(request.getParameter("gPrice"));
        int gNumber=Integer.parseInt(request.getParameter("gNumber"));
        BigDecimal gSumPrice=gPrice.multiply(new BigDecimal(gNumber));
        goMall.setgName(request.getParameter("gName"));goMall.setgPrice(gPrice);
        goMall.setgNumber(gNumber);goMall.setgSumPrice(gSumPrice);
        int gId=Integer.parseInt(request.getParameter("gId"));
        goMall.setgId(gId);
        if(dao.updategoMall(goMall)>0){
            response.getWriter().print("修改成功！");
        }else{
            response.getWriter().print("修改失败！");
        }
    }
    //修改数量加一
    @RequestMapping(value = "/updategoMallNumberAdd",method = RequestMethod.POST)
    @ResponseBody
    public void updategoMallNumberAdd(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        int gId=Integer.parseInt(request.getParameter("gId"));
        if(dao.updategoMallNumberAdd(gId)>0){
            response.getWriter().print("修改成功！");
        }else{
            response.getWriter().print("修改失败！");
        }
    }//修改数量加减一
    @RequestMapping(value = "/updategoMallNumberRemove",method = RequestMethod.POST)
    @ResponseBody
    public void updategoMallNumberRemove(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        int gId=Integer.parseInt(request.getParameter("gId"));
        if(dao.updategoMallNumberRemove(gId)>0){
            response.getWriter().print("修改成功！");
        }else{
            response.getWriter().print("修改失败！");
        }
    }
}
