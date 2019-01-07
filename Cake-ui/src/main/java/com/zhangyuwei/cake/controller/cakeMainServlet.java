package com.zhangyuwei.cake.controller;

import com.zhangyuwei.cake.dao.IcakeMainDao;
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
import java.lang.reflect.Array;
import java.util.*;

@Controller
@RequestMapping("X-admin/CakeMainServlet")
public class cakeMainServlet {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    IcakeMainDao dao=ctx.getBean(IcakeMainDao.class);
    //查询蛋糕有关的一切信息(多对多)
    @RequestMapping(value = "/selectCakeInformationAll",method = RequestMethod.POST)
    @ResponseBody
    public List<SmallTypeInformation> selectCakeInformationAll(){
        return dao.selectCakeInformationAll();
    }
    //查询蛋糕类别，蛋糕口味和糕信息表结合
    @RequestMapping(value = "/selectCaAndCaAndMo",method = RequestMethod.POST)
    @ResponseBody
    public List<CakeInformation> selectCaAndCaAndMo(@RequestBody List<Object> objectList){
        Map<String,Object> map=new HashMap<>();
        Object page=objectList.get(0);Object limit=objectList.get(1);
        int pages=(int)page;int limits=(int)limit;
        map.put("page",(pages-1)*limits);
        map.put("limit",limits);
        map.put("cName",objectList.get(2));map.put("ctName",objectList.get(3));
        String cState="";
        if(objectList.get(5).equals("上架")){
            cState="1";
        }else if(objectList.get(5).equals("下架")){
            cState="0";
        }
        map.put("mtName",objectList.get(4));map.put("cState",cState);
        List<CakeInformation> entity=dao.selectCaAndCaAndMo(map);
        return entity;
    }
    //查询蛋糕类别，蛋糕口味和糕信息表结合数量
    @RequestMapping(value = "/selectCaAndCaAndMoCount",method = RequestMethod.POST)
    @ResponseBody
    public int selectCaAndCaAndMoCount(@RequestBody List<Object> objectList){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("cName",objectList.get(0));map.put("ctName",objectList.get(1));
        String cState="";
        if(objectList.get(3).equals("上架")){
            cState="1";
        }else if(objectList.get(3).equals("下架")){
            cState="0";
        }
        map.put("mtName",objectList.get(2));map.put("cState",cState);
        return dao.selectCaAndCaAndMoCount(map);
    }
    //修改蛋糕状态
    @RequestMapping(value = "/updateCakeInformation",method = RequestMethod.POST)
    @ResponseBody
    public void updateCakeInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        int cId=Integer.parseInt(request.getParameter("cId"));
        int cState=Integer.parseInt(request.getParameter("cState"));
        CakeInformation cakeInformation=new CakeInformation();
        cakeInformation.setcId(cId);cakeInformation.setcState(cState);
        if(dao.updateCakeInformation(cakeInformation)>0){
            response.getWriter().print("修改成功！");
        }else{
            response.getWriter().print("修改失败!");
        }
    }
    //查询商品口味和商品类型到下拉框
    @RequestMapping(value = "/selectCakeTypeAndMouseTypeToSelect",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectCakeTypeAndMouseTypeToSelect(/*@RequestBody List<Integer> objectId,*/HttpServletRequest request){
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
    //查询商品口味和商品类型到下拉框2
    @RequestMapping(value = "/selectCakeTypeAndMouseTypeToSelect2",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectCakeTypeAndMouseTypeToSelect2(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        //查询商品类型表
        List<CakeType> cakeTypeList=dao.selectCakeInformationCakeType();
        //查询口味表
        List<MouseType> mouseTypeList=dao.selectCakeInformationMouseType();
        //查询蛋糕小类型表
        List<SmallCakeType> smallCakeTypes=dao.selectSmallCakeType();
        //根据蛋糕id查出蛋糕与蛋糕下类型对应表
        int cId=Integer.parseInt(request.getParameter("cId"));
        //根据id获得蛋糕富文本中的值
        List<CakeInformation> cakeInformationList=dao.selectCakeInformationDescById(cId);
        //获得蛋糕小类型需要选中的值
        List<SmallTypeInformation> smallTypeInformations=dao.selectSmallTypeInformation(cId);
        map.put("cakeType",cakeTypeList);map.put("mouseType",mouseTypeList);
        map.put("smallCakeType",smallCakeTypes);
        map.put("cakeInformation",cakeInformationList);
        map.put("smallTypeInformation",smallTypeInformations);
        return map;
    }
    //根据蛋糕id查出蛋糕与蛋糕下类型对应表(往session中存放id
    @RequestMapping(value = "/selectSmallTypeInformation",method = RequestMethod.POST)
    @ResponseBody
    public void selectSmallTypeInformation(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int cId=Integer.parseInt(request.getParameter("cId"));
        //设置sessionName值
        HttpSession session=request.getSession();
        session.setAttribute("SessioncId",cId);
        System.out.println(request.getSession().getAttribute("SessioncId"));
        if(cId>0){
            response.getWriter().print("添加成功!");
        }else{
            response.getWriter().print("添加失败!");
        }
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
        upLoad.setCode(0);
        upLoad.setMsg("上传成功！");
        upLoad.setData(tempFile.getName());
        return upLoad;
    }
    //添加数据到蛋糕信息表//添加数据到蛋糕与蛋糕小类型对应表
    @RequestMapping(value = "/insertCakeInformation",method = RequestMethod.POST)
    @ResponseBody
    public void insertCakeInformation(@RequestBody List<CakeInformation> objectList,HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        CakeInformation cakeInformation=new CakeInformation();
        cakeInformation.setcName(objectList.get(0).getcName());cakeInformation.setcNameEnglish(objectList.get(0).getcNameEnglish());
        cakeInformation.setcDecription(objectList.get(0).getcDecription());cakeInformation.setcPicture(objectList.get(0).getcPicture());
        cakeInformation.setcDesc(objectList.get(0).getcDesc());cakeInformation.setCtId(objectList.get(0).getCtId());
        cakeInformation.setMtId(objectList.get(0).getMtId());
        int row=dao.insertCakeInformation(cakeInformation);

        if(row>0){
            response.getWriter().print("添加成功!");
        }else{
            response.getWriter().print("添加失败!");
        }
    }
    //添加数据到蛋糕与蛋糕小类型对应表
    @RequestMapping(value = "/insertSmallTypeInformationSome",method = RequestMethod.POST)
    @ResponseBody
    public void insertSmallTypeInformationSome(/*@RequestBody List<Integer> list,*/HttpServletResponse response,HttpServletRequest request) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        //查出商品的id(最后一条的ID)
        int cId=dao.selectLastId().get(dao.selectLastId().size()-1).getcId();
        //多添加商品小类型
        String stName=request.getParameter("stName");
        System.out.println(stName);
        String[] arr=stName.split(",");
        int[] arr1=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            if(arr[i].equals("儿童")){
                arr1[i]=1;
            }else if(arr[i].equals("聚会")){
                arr1[i]=2;
            }else if(arr[i].equals("相识")){
                arr1[i]=3;
            }else if(arr[i].equals("情侣")){
                arr1[i]=4;
            }
        }
        List<SmallTypeInformation> smallTypeInformationList=new ArrayList<SmallTypeInformation>();
        for (int i=0;i<arr1.length;i++){
            smallTypeInformationList.add(new SmallTypeInformation(cId,arr1[i]));
        }
        if(dao.insertSmallTypeInformationSome(smallTypeInformationList)>=0){
            response.getWriter().print("添加成功!");
        }else{
            response.getWriter().print("添加失败!");
        }
    }
    //修改蛋糕信息
    @RequestMapping(value = "/updateCakeInformationAll",method = RequestMethod.POST)
    @ResponseBody
    public void updateCakeInformationAll(@RequestBody List<CakeInformation> cakeInformationList,HttpServletResponse response,HttpServletRequest request) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        CakeInformation cakeInformation=new CakeInformation();
        cakeInformation.setcName(cakeInformationList.get(0).getcName());cakeInformation.setcNameEnglish(cakeInformationList.get(0).getcNameEnglish());
        cakeInformation.setcDecription(cakeInformationList.get(0).getcDecription());cakeInformation.setcPicture(cakeInformationList.get(0).getcPicture());
        cakeInformation.setcDesc(cakeInformationList.get(0).getcDesc());cakeInformation.setCtId(cakeInformationList.get(0).getCtId());
        cakeInformation.setMtId(cakeInformationList.get(0).getMtId());
        cakeInformation.setcId(cakeInformationList.get(0).getcId());
        int row=dao.updateCakeInformationAll(cakeInformation);
        if(row>0){
            response.getWriter().print("修改成功!");
        }else{
            response.getWriter().print("修改失败!");
        }
    }
    //修改蛋糕与蛋糕小类型对应表
    @RequestMapping(value = "/updateSmallTypeInformation",method = RequestMethod.POST)
    @ResponseBody
    public void updateSmallTypeInformation(HttpServletResponse response,HttpServletRequest request) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        //多添加商品小类型
        String stName=request.getParameter("stName");
        int cId=Integer.parseInt(request.getParameter("cId"));
        String[] arr=stName.split(",");
        int[] arr1=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            if(arr[i].equals("儿童")){
                arr1[i]=1;
            }else if(arr[i].equals("聚会")){
                arr1[i]=2;
            }else if(arr[i].equals("相识")){
                arr1[i]=3;
            }else if(arr[i].equals("情侣")){
                arr1[i]=4;
            }
        }
        //先删除原本的
        dao.deleteSmallTypeInformationbyId(cId);
        //添加勾选的
        List<SmallTypeInformation> smallTypeInformationList=new ArrayList<SmallTypeInformation>();
        for (int i=0;i<arr1.length;i++){
            smallTypeInformationList.add(new SmallTypeInformation(cId,arr1[i]));
        }
        if(dao.insertSmallTypeInformationSome(smallTypeInformationList)>=0){
            response.getWriter().print("修改成功!");
        }else{
            response.getWriter().print("修改失败!");
        }
    }
    /*导出数据到Excel表格*/
    @RequestMapping(value = "/joinxml",method = RequestMethod.GET)
    @ResponseBody
    public void joinXml(HttpServletResponse response) throws IOException {
        //数据的来源
        List<CakeInformation> entity=dao.selectCaAndCaAndMoNoPage();
        System.out.println(entity);
        //设置标题
        String head = "商品信息详细展示";
        //设置表头行
        String[] headrow = {"编号", "蛋糕名称", "英文名","简述","商品类别","口味","状态"};
        if (null != entity && entity.size() > 0) {
            String fileName = "商品信息.xls";//定义导出头
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
            //CellStyle cellStyle = book.createCellStyle();

            //cellStyle.setDataFormat(book.createDataFormat().getFormat("yyyy-MM-dd"));

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
                int raState=entity.get(i).getcState();
                String cStates="";
                if(raState==0){
                    cStates="下架";
                }else {
                    cStates="上架";
                }
                row.createCell((short) 0).setCellValue(entity.get(i).getcId());
                row.createCell((short) 1).setCellValue(entity.get(i).getcName());
                row.createCell((short) 2).setCellValue(entity.get(i).getcNameEnglish());
                row.createCell((short) 3).setCellValue(entity.get(i).getcDecription());
                row.createCell((short) 4).setCellValue(entity.get(i).getCakeType().getCtName());
                row.createCell((short) 5).setCellValue(entity.get(i).getMouseType().getMtName());
                row.createCell((short) 6).setCellValue(cStates);
            }
            //写出流  刷新缓冲流  关闭流对象
            book.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }
}
