package com.zhangyuwei.cake.controller;

import com.zhangyuwei.cake.dao.IimageDao;
import com.zhangyuwei.cake.entities.CakeInformation;
import com.zhangyuwei.cake.entities.CakePicture;
import com.zhangyuwei.cake.entities.MultiUpLoad;
import com.zhangyuwei.cake.entities.UpLoad;
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
import java.util.List;

@Controller
@RequestMapping("X-admin/ImageServlet")
public class imageServlet {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    IimageDao dao=ctx.getBean(IimageDao.class);
    /*文件上传*/
    @RequestMapping(value = "fileSaves",method = RequestMethod.POST)
    @ResponseBody
    public MultiUpLoad fileSaves(MultipartFile[] file, HttpServletRequest request) throws Exception{
        System.out.println("-------文件上传---------");
        //文件存放的位置
        String path=request.getServletContext().getRealPath("/images");
        System.out.println(path);
        File fi=new File(path);
        if(!fi.exists()){
            fi.mkdir();
        }
        File[] tempFile=null;
        System.out.println(tempFile);
        List<Object> list=new ArrayList<>();
        System.out.println("dddd");
        for(int i=0;i<tempFile.length;i++){
            System.out.println("-我是循环里面的--");
            tempFile[i]=new File(path, file[i].getOriginalFilename());
            file[i].transferTo(tempFile[i]);
            list.add(tempFile[i].getName());
            System.out.println(tempFile[i].getName());
        }
        //System.out.println(tempFile.getName());
        MultiUpLoad upLoad=new MultiUpLoad();
        upLoad.setCode(0);
        upLoad.setMsg("上传成功！");
        upLoad.setData(list);
        return upLoad;
    }
    /*文件上传*/
    @RequestMapping(value = "fileSave",method = RequestMethod.POST)
    @ResponseBody
    public UpLoad fileSave(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //文件存放的位置
        String path=request.getServletContext().getRealPath("/images");
        File fi=new File(path);
        if(!fi.exists()){
            fi.mkdir();
        }
        File tempFile=new File(path, file.getOriginalFilename());
        file.transferTo(tempFile);
        System.out.println(tempFile.getName());
        UpLoad upLoad=new UpLoad();
        int cId=(int)request.getSession().getAttribute("cakePictureCid");
        int countcId=dao.selectCakePictureCount(cId);
        upLoad.setCode(1);
        upLoad.setMsg("上传成功!");
        upLoad.setCount(countcId);
        upLoad.setData(tempFile.getName());
        return upLoad;
    }
    /*查询所有商品*/
    @RequestMapping(value = "/selectCakeInformation",method = RequestMethod.POST)
    @ResponseBody
    public List<CakeInformation> selectCakeInformation(HttpServletRequest request){
        //设置sessionName值(设置修改后的ID值，停留在当前商品的图片页)
        /*HttpSession session=request.getSession();
        session.setAttribute("cakePictureCids","");*/
        //记住（重新部署之后值会消失,所以图片管理会出错）
        return dao.selectCakeInformation();
    }
    /*查询图片表，根据商品id*/
    @RequestMapping(value = "/selectCakePicture",method = RequestMethod.POST)
    @ResponseBody
    public List<CakePicture> selectCakePicture(@RequestBody List<CakePicture> cakePictureList,HttpServletRequest request){
        int cId=cakePictureList.get(0).getcId();
        //设置sessionName值(文件上传是需要用到)
        HttpSession session=request.getSession();
        session.setAttribute("cakePictureCid",cId);
        //判断当前商品页Id页是否为空，当然在登录时要设置其值为空，否则找不到报500，
        if(!request.getSession().getAttribute("cakePictureCids").equals("")){
            cId=(int)request.getSession().getAttribute("cakePictureCids");
            //取得值之后记得重新设置为空
            request.getSession().setAttribute("cakePictureCids","");
        }
        request.getSession().setAttribute("cakePictureCids","");
        String cpPicture=cakePictureList.get(0).getCpPicture();
        CakePicture cakePicture=new CakePicture();
        cakePicture.setcId(cId);cakePicture.setCpPicture(cpPicture);
        return  dao.selectCakePicture(cakePicture);
    }
    //添加图片（多添加）
    @RequestMapping(value = "/insertCakePictureSome",method = RequestMethod.POST)
    @ResponseBody
    public void insertCakePictureSome(HttpServletRequest request,HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        String cpPicture=request.getParameter("cpPicture");
       int cId=Integer.parseInt(request.getParameter("cId"));
        int countId=Integer.parseInt(request.getParameter("countId"));
        System.out.println(cpPicture+","+cId+","+countId);
        if(dao.selectCakePictureCount(cId)+countId>4){
            response.getWriter().print("图片上传失败，数量超过了最大数量!");
        }else{
            String[] arr=cpPicture.split(",");
            List<CakePicture> cakePictureList=new ArrayList<CakePicture>();
            for (int i=0;i<arr.length;i++){
                cakePictureList.add(new CakePicture(arr[i],cId));
            }
            if(dao.insertCakePictureSome(cakePictureList)>=arr.length){
                response.getWriter().print("图片上传成功!");
            }else{
                response.getWriter().print("图片上传失败!");
            }
        }
    }
    //修改图片
    @RequestMapping(value = "/updateCakePictureById",method = RequestMethod.POST)
    @ResponseBody
    public void updateCakePictureById(HttpServletRequest request,HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        String cpPicture=request.getParameter("cpPicture");
        int cId=Integer.parseInt(request.getParameter("cId"));
        String cpTitle=request.getParameter("cpTitle");
        System.out.println(cpTitle);
        CakePicture cakePicture=new CakePicture();
        cakePicture.setCpTitle(cpTitle);cakePicture.setcId(cId);
        cakePicture.setCpPicture(cpPicture);
        if(dao.updateCakePictureById(cakePicture)>0){
            //设置sessionName值(设置修改后的ID值，停留在当前商品的图片页)
            HttpSession session=request.getSession();
            session.setAttribute("cakePictureCids",cId);
            response.getWriter().print("修改成功!");
        }else{
            response.getWriter().print("修改失败!");
        }
    }
    //删除图片
    @RequestMapping(value = "/deleteCakePictureById",method = RequestMethod.POST)
    @ResponseBody
    public void deleteCakePictureById(HttpServletRequest request,HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        String cpPicture=request.getParameter("cpPicture");
        int cId=Integer.parseInt(request.getParameter("cId"));
        CakePicture cakePicture=new CakePicture();
        cakePicture.setcId(cId);
        cakePicture.setCpPicture(cpPicture);
        if(dao.deleteCakePictureById(cakePicture)>0){
            //设置sessionName值(设置修改后的ID值，停留在当前商品的图片页)
            HttpSession session=request.getSession();
            session.setAttribute("cakePictureCids",cId);
            response.getWriter().print("删除成功!");
        }else{
            response.getWriter().print("删除失败!");
        }
    }
}
