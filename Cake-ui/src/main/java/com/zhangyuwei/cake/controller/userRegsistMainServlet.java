package com.zhangyuwei.cake.controller;

import com.zhangyuwei.cake.dao.IuserMainDao;
import com.zhangyuwei.cake.dao.IuserRegsistMainDao;
import com.zhangyuwei.cake.entities.*;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("cake/UserRegsistMainServlet")
public class userRegsistMainServlet {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    IuserRegsistMainDao dao=ctx.getBean(IuserRegsistMainDao.class);
    //用户注册
    @RequestMapping("/insertRegsist")
    @ResponseBody
    public void insertRegsist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        String rPhone=request.getParameter("rPhone");
        String rPassword=request.getParameter("rPassword");
        Regsist regsist=new Regsist();
        regsist.setrPhone(rPhone);regsist.setrPassword(rPassword);
        if(dao.insertRegsist(regsist)>0){
            response.getWriter().print("注册成功！");
        }else{
            response.getWriter().print("注册失败!");
        }
    }
    //用户登录
    @RequestMapping("/selectRegsist")
    @ResponseBody
    public void selectRegsist(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        String rPhone=request.getParameter("rPhone");
        String rPassword=request.getParameter("rPassword");
        Regsist regsist=new Regsist();
        regsist.setrPhone(rPhone);regsist.setrPassword(rPassword);
        int row=dao.selectRegsist(regsist);String rows=row+"";
        if(rows.equals("1")){
            //设置用户的登录名session
            HttpSession session=request.getSession();
            session.setAttribute("rPhone",rPhone);
            //设置用户的id，在后面的购物车等多处要用到
            int rId=dao.selectRegsistId(rPhone);
            session.setAttribute("rId",rId);
            response.getWriter().print("登录成功!");
        }else{
            response.getWriter().print("登录失败!");
        }
    }
    //根据电话号码进行登录
    @RequestMapping("/selectRegsistByrPhone")
    @ResponseBody
    public void selectRegsistByrPhone(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        String rPhone=request.getParameter("rPhone");
        int row=dao.selectRegsistByrPhone(rPhone);String rows=row+"";
        if(rows.equals("1")){
            //设置用户的登录名session
            HttpSession session=request.getSession();
            session.setAttribute("rPhone",rPhone);
            //设置用户的id，在后面的购物车等多处要用到
            int rId=dao.selectRegsistId(rPhone);
            session.setAttribute("rId",rId);
            response.getWriter().print("登录成功!");
        }else{
            response.getWriter().print("登录失败!");
        }
    }
    //获取用户的电话号码
    @RequestMapping(value = "/getPhone",method = RequestMethod.GET)
    @ResponseBody
    public String getPhone(HttpServletRequest request){
        //获得session中的值
        HttpSession session=request.getSession();
        String rPhone=(String)session.getAttribute("rPhone");
        return rPhone;
    }
    //查询购物车中的信息(根据用户编号和购物车状态)
    @RequestMapping(value = "/selectShoppingByCidAndSstate",method = RequestMethod.POST)
    @ResponseBody
    public List<Shopping> selectShoppingByCidAndSstate(HttpServletRequest request){
        //获得session中的值
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        return dao.selectShoppingByCidAndSstate(rId);
    }
    //查询赠送餐具数
    @RequestMapping(value = "/selectPricePoundageByCidAndPoundage",method = RequestMethod.POST)
    @ResponseBody
    public int selectPricePoundageByCidAndPoundage(HttpServletRequest request){
        int cId=Integer.parseInt(request.getParameter("cId"));
        BigDecimal sPoundage=new BigDecimal(request.getParameter("sPoundage"));
        PricePoundage pricePoundage=new PricePoundage();
        pricePoundage.setcId(cId);pricePoundage.setPpPoundage(sPoundage);
        return dao.selectPricePoundageByCidAndPoundage(pricePoundage);
    }
    //数量减一
    @RequestMapping(value = "/updateSnumberJian",method = RequestMethod.POST)
    @ResponseBody
    public void updateSnumberJian(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int sId=Integer.parseInt(request.getParameter("sId"));
        if(dao.updateSnumberJian(sId)>0){
            response.getWriter().print("减一成功！");
        }else{
            response.getWriter().print("减一失败");
        }
    }
    //数量加一
    @RequestMapping(value = "/updateSnumberJia",method = RequestMethod.POST)
    @ResponseBody
    public void updateSnumberJia(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int sId=Integer.parseInt(request.getParameter("sId"));
        if(dao.updateSnumberJia(sId)>0){
            response.getWriter().print("加一成功！");
        }else{
            response.getWriter().print("加一失败！");
        }
    }
    //删除一条
    @RequestMapping(value = "/deleteShopping",method = RequestMethod.POST)
    @ResponseBody
    public void deleteShopping(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int sId=Integer.parseInt(request.getParameter("sId"));
        if(dao.deleteShopping(sId)>0){
            response.getWriter().print("删除成功！");
        }else{
            response.getWriter().print("删除失败！");
        }
    }
    //加入购物车
    @RequestMapping(value = "/insertShopping",method = RequestMethod.POST)
    @ResponseBody
    public void insertShopping(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //解决乱码
        response.setCharacterEncoding("utf-8");
        Shopping shopping=new Shopping();
        int cId=Integer.parseInt(request.getParameter("cId"));
        shopping.setcId(cId);//商品编号
        BigDecimal sPrice=new BigDecimal(request.getParameter("sPrice"));
        shopping.setsPrice(sPrice);//商品单价
        int sPoundage=Integer.parseInt(request.getParameter("sPoundage"));
        shopping.setsPoundage(sPoundage);//磅数
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        shopping.setrId(rId);//用户编号,从session中获取
        shopping.setsBoard("");//生日牌，一开始是为空，当然你可以进行修改
        int count=dao.selectCidState(shopping);//查询出商品的个数
        int counts=0;
        if((count+"").equals("0")){
            counts=1;
            BigDecimal sSumPrice=sPrice.multiply(new BigDecimal(counts));
            shopping.setsSumPrice(sSumPrice);//总价
            if(dao.insertShopping(shopping)>0){
                response.getWriter().print("加入购物车成功!");
            }else{
                response.getWriter().print("加入购物车失败!");
            }
        }else{
            if(dao.updateSumPrice(shopping)>0){
                response.getWriter().print("加入购物车成功!");
            }else{
                response.getWriter().print("加入购物车失败!");
            }
        }
    }
    //查询商品总额,当前用户，购物车状态
    @RequestMapping(value = "/selectShoppingSumPrice",method = RequestMethod.POST)
    @ResponseBody
    public String selectShoppingSumPrice(HttpServletRequest request){
        //获取用户的id
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        return dao.selectShoppingSumPrice(rId);
    }
    //查询所有用户所对应的用户
    @RequestMapping(value = "/selectNewUserInformation",method = RequestMethod.POST)
    @ResponseBody
    public List<NewUserInformation> selectNewUserInformation(HttpServletRequest request){
        //获取用户的id
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        return dao.selectNewUserInformation(rId);
    }
    //添加用户（一个对应多个）
    @RequestMapping(value = "/insertNewUserInformation",method = RequestMethod.POST)
    @ResponseBody
    public void insertNewUserInformation(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        String nName=request.getParameter("nName");
        String nPhone=request.getParameter("nPhone");
        String nSen=request.getParameter("nSen");
        String nCity=request.getParameter("nCity");
        String nSite=request.getParameter("nSite");
        String nDesc=request.getParameter("nDesc");
        NewUserInformation newUserInformation=new NewUserInformation();
        newUserInformation.setnName(nName);//名字
        newUserInformation.setnPhone(nPhone);//电话
        newUserInformation.setnSen(nSen);//省
        newUserInformation.setnCity(nCity);//县
        newUserInformation.setnSite(nSite);//乡
        newUserInformation.setnDesc(nDesc);//详细位置
        //获取用户的id
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        newUserInformation.setrId(rId);//用户编号
        if(dao.insertNewUserInformation(newUserInformation)>0){
            response.getWriter().print("添加成功!");
        }else{
            response.getWriter().print("添加失败!");
        }
    }
    //修改新增用户状态变为1
    @RequestMapping(value = "/updateNewUserInformation",method = RequestMethod.POST)
    @ResponseBody
    public void updateNewUserInformation(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        int nId=Integer.parseInt(request.getParameter("nId"));
        if(dao.updateNewUserInformation(nId)>0){
            response.getWriter().print("修改成功!");
        }else{
            response.getWriter().print("修改失败!");
        }
    }
    //修改新增用户状态全部变为0
    @RequestMapping(value = "/updateNewUserInformation2",method = RequestMethod.POST)
    @ResponseBody
    public void updateNewUserInformation2(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        //获取用户的id
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        if(dao.updateNewUserInformation2(rId)>0){
            response.getWriter().print("修改成功!");
        }else{
            response.getWriter().print("修改失败!");
        }
    }
    //根据状态查询用户信息
    @RequestMapping(value = "/selectNewUserInformationByNstate",method = RequestMethod.POST)
    @ResponseBody
    public List<NewUserInformation> selectNewUserInformationByNstate(HttpServletRequest request){
        //获取用户的id
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        return dao.selectNewUserInformationByNstate(rId);
    }
    //根据状态查询用户信息2
    @RequestMapping(value = "/selectNewUserInformationByNstate2",method = RequestMethod.POST)
    @ResponseBody
    public List<NewUserInformation> selectNewUserInformationByNstate2(HttpServletRequest request){
        //获取用户的id
        HttpSession session=request.getSession();
        int rId=(int)session.getAttribute("rId");
        return dao.selectNewUserInformationByNstate2(rId);
    }

}
