package com.zhangyuwei.cake.controller;

import com.zhangyuwei.cake.commons.util.MD5Utils;
import com.zhangyuwei.cake.commons.util.R;
import com.zhangyuwei.cake.dao.IcakeDao;
import com.zhangyuwei.cake.entities.RegsistAdmin;
import com.zhangyuwei.cake.service.IcakeService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.omg.PortableServer.IdAssignmentPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
//@Service
@RequestMapping("X-admin/CakeServlet")
//@RestController
public class cakeServlet {
   /*@Autowired
   IcakeService service;*/
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    IcakeDao dao=ctx.getBean(IcakeDao.class);
    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("message","测试");
        return "index";
    }
    //登录
    @RequestMapping(value = "/selectRegsistAdminByUserAndPassword",method = RequestMethod.GET)
    @ResponseBody
    public void selectRegsistAdminByUserAndPassword(HttpServletRequest request,HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        //获得管理员账号与密码
        String raName=request.getParameter("raName");
        String raPassword=request.getParameter("raPassword");
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        String MD5Password=MD5Utils.getMD5String(raPassword);
        regsistAdmin.setRaName(raName);
        regsistAdmin.setRaPassword(MD5Password);
        int row = dao.selectRegsistAdminByUserAndPassword(regsistAdmin).size();
        //登录失败查不出转态
        if (row == 1) {
            //获得状态(注销了不允许登录)
            int raState=dao.selectRegsistAdminByUserAndPassword(regsistAdmin).get(0).getRaState();
            if(raState == 1) {
                //设置sessionName值(用户名)
                HttpSession session=request.getSession();
                session.setAttribute("sessionName",raName);
                //设置sessionName值(图片中要用到的id)
                session.setAttribute("cakePictureCids","");
                session.setAttribute("pricePoundageCids","");
                response.getWriter().print("登录成功!");
            }else{
                response.getWriter().print("登录失败,你已经注销!");
            }
        } else {
            response.getWriter().print("登录失败!");
        }
    }
    //查询，根据其权限进行查询
    @RequestMapping(value = "/selectRegsistAdminByRole",method = RequestMethod.POST)
    @ResponseBody
    public List<RegsistAdmin> selectRegsistAdminByRole(HttpServletRequest request,HttpServletResponse response){
        //获取姓名
        //获取session中的raName值
        /*Object sessionName=request.getSession().getAttribute("sessionName");
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        regsistAdmin.setRaRole(raRole);regsistAdmin.setRaName((String)sessionName);*/
        //获取raRole角色
        /*String raRole=dao.selectAdminByNameOfRole(regsistAdmin).get(0).getRaRole();
        return dao.selectRegsistAdminByRole(regsistAdmin);*/
        return null;
    }
    //查询，分页
    @RequestMapping(value = "/selectAdminByPage",method = RequestMethod.POST)
    @ResponseBody
    public List<RegsistAdmin> selectAdminByPage(@RequestBody List<Object> objectList,HttpServletRequest request){
        System.out.println(objectList.get(2));
        Map<String,Object> map=new HashMap<>();
        Object page=objectList.get(0);Object limit=objectList.get(1);
        int pages=(int)page;int limits=(int)limit;
        RegsistAdmin regsistAdmin1=new RegsistAdmin();
        //获取session中的raName值
        //String sessionName="addAdmin13";
        Object sessionName=request.getSession().getAttribute("sessionName");
        regsistAdmin1.setRaName((String) sessionName);
        //获取session中的raRole值
       // String sessionRole="超级管理员";
        List<RegsistAdmin> adminList=dao.selectAdminByNameOfRole(regsistAdmin1);
        String sessionRole=adminList.get(0).getRaRole();
        if(sessionRole.equals("超级管理员")){
            map.put("raName",objectList.get(2));
        }else{
            //获取session中的值
            map.put("raName",sessionName);
        }
        map.put("raRole",sessionRole);
        map.put("page",(pages-1)*limits);
        map.put("limit",limits);
        List<RegsistAdmin> entity=dao.selectAdminByPage(map);
        return entity;
    }
    //查询数量
    @RequestMapping(value = "/selectAdminCount",method = RequestMethod.POST)
    @ResponseBody
    public int selectAdminCount(HttpServletRequest request){
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        RegsistAdmin regsistAdmin1=new RegsistAdmin();
        String raName=request.getParameter("raName");
        //获取session中的raName值
        //String sessionName="addAdmin13";
        Object sessionName=request.getSession().getAttribute("sessionName");
        regsistAdmin1.setRaName((String) sessionName);
        //获取session中的raRole值
        //String sessionRole="超级管理员";
        List<RegsistAdmin> adminList=dao.selectAdminByNameOfRole(regsistAdmin1);
        String sessionRole=adminList.get(0).getRaRole();
        System.out.println("-----");
        System.out.println(sessionRole);
        if(sessionRole.equals("超级管理员")){
            System.out.println("---超级管理员---");
            regsistAdmin.setRaName(raName);
        }else{
            //获取session中的值
            regsistAdmin.setRaName((String)sessionName);
        }
        regsistAdmin.setRaRole(sessionRole);
        return dao.selectAdminCount(regsistAdmin);
    }
    //修改管理员状态
    @RequestMapping(value = "/updateState",method = RequestMethod.POST)
    @ResponseBody
    public void updateState(HttpServletRequest request,HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        //获取状态
        int raState=Integer.parseInt(request.getParameter("raState"));
        //获取ID
        int raId=Integer.parseInt(request.getParameter("raId"));
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        regsistAdmin.setRaId(raId);
        regsistAdmin.setRaState(raState);
        if(dao.updateState(regsistAdmin)>0){
            response.getWriter().print("修改成功!");
        }else{
            response.getWriter().print("修改失败!");
        }
    }
    //添加管理员
    @RequestMapping(value = "/insertRegsistAdmin",method = RequestMethod.POST)
    @ResponseBody
    public void insertRegsistAdmin(HttpServletRequest request,HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        //获取姓名
        String raName=request.getParameter("raName");
        //获取电话号码
        String raPhone=request.getParameter("raPhone");
        //获取邮箱
        String raEmail=request.getParameter("raEmail");
        //设置密码
        String raPassword="000000";
        String MD5Password=MD5Utils.getMD5String(raPassword);
        //获取地位
        String raRole=request.getParameter("raRole");
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        regsistAdmin.setRaName(raName);regsistAdmin.setRaPhone(raPhone);
        regsistAdmin.setRaEmail(raEmail);regsistAdmin.setRaPassword(MD5Password);
        regsistAdmin.setRaRole(raRole);
        //判断该登录名是否被注册
        int countName=dao.selectAdminByNameIfRegsist(regsistAdmin);
        if(countName==0) {
            if (dao.insertRegsistAdmin(regsistAdmin) > 0) {
                response.getWriter().print("添加成功！");
            } else {
                response.getWriter().print("添加失败！");
            }
        }else{
            response.getWriter().print("该用户名已被注册，请重新输入！");
        }
    }
    //修改管理员
    @RequestMapping("/updateRegsistAdmin")
    @ResponseBody
    public void updateRegsistAdmin(HttpServletRequest request,HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        //获取姓名
        String raName=request.getParameter("raName");
        //获取电话号码
        String raPhone=request.getParameter("raPhone");
        //获取邮箱
        String raEmail=request.getParameter("raEmail");
        //获取地位
        int raId=Integer.parseInt(request.getParameter("raId"));
        String raRole=request.getParameter("raRole");
        RegsistAdmin regsistAdmin=new RegsistAdmin();
        regsistAdmin.setRaName(raName);regsistAdmin.setRaPhone(raPhone);
        regsistAdmin.setRaEmail(raEmail);regsistAdmin.setRaRole(raRole);
        regsistAdmin.setRaId(raId);
        if(dao.updateRegsistAdmin(regsistAdmin)>0){
            response.getWriter().print("修改成功！");
        }else{
            response.getWriter().print("修改失败！");
        }
    }
    //多项重置密码
    @RequestMapping(value = "/updateRegsistAdminPassword",method = RequestMethod.POST)
    @ResponseBody
    public void updateRegsistAdminPassword(@RequestBody List<Integer> integerList,HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        //拿到所有的重置密码ID
        if(dao.updateRegsistAdminPassword(integerList)==integerList.size()){
            response.getWriter().print("重置密码成功！");
        }else{
            response.getWriter().print("重置密码失败！");
        }
    }
    /*导出数据到Excel表格*/
    @RequestMapping(value = "/joinxml",method = RequestMethod.GET)
    @ResponseBody
    public void joinXml(HttpServletResponse response) throws IOException {
        //数据的来源
        List<RegsistAdmin> entity=dao.selectAllRegsistAdmin();
        //设置标题
        String head = "管理员信息详细展示";
        //设置表头行
        String[] headrow = {"编号", "用户名", "手机号","邮箱","角色","注册日期","状态"};
        if (null != entity && entity.size() > 0) {
            String fileName = "管理员信息.xls";//定义导出头
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));    //设置文件头编码格式
            response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");//设置类型
            response.setHeader("Cache-Control", "no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头
            //创建工作簿HSSFWorkbook 对象
            HSSFWorkbook book = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = book.createSheet();
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet.createRow(0);
            //由工作簿创建表HSSFSheet对象
            CellStyle cellStyle = book.createCellStyle();

            cellStyle.setDataFormat(book.createDataFormat().getFormat("yyyy-MM-dd"));

            //设置表头
            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue(head);
            row = sheet.createRow(1);
            for (int i = 0; i < headrow.length; i++) {
                cell = row.createCell((short) i);
                cell.setCellValue(headrow[i]);
            }

            for (int i = 0; i < entity.size(); i++) {
                //实体类对象
                row = sheet.createRow((i + 2));
                Date times=entity.get(i).getRaBirthday();
                int raState=entity.get(i).getRaState();
                String raStates="";
                if(raState==0){
                    raStates="注销";
                }else {
                    raStates="存在";
                }
                row.createCell((short) 0).setCellValue(entity.get(i).getRaId());
                row.createCell((short) 1).setCellValue(entity.get(i).getRaName());
                row.createCell((short) 2).setCellValue(entity.get(i).getRaPhone());
                row.createCell((short) 3).setCellValue(entity.get(i).getRaEmail());
                row.createCell((short) 4).setCellValue(entity.get(i).getRaRole());
                row.createCell((short) 5).setCellValue(times.toLocaleString());
                row.createCell((short) 6).setCellValue(raStates);
            }
            //写出流  刷新缓冲流  关闭流对象
            book.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }

}
