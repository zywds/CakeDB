package com.zhangyuwei.cake.controller;

import com.sun.deploy.net.HttpResponse;
import com.zhangyuwei.cake.dao.IcakeMainDao;
import com.zhangyuwei.cake.dao.IdatabaseBackupDao;
import com.zhangyuwei.cake.entities.DatabaseBackup;
import com.zhangyuwei.cake.entities.HistoryShoppingDiary;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhangyuwei.utils.MySQLDatabaseBackup.backup;

@Controller
@RequestMapping("X-admin/DatabaseBackupServlet")
public class databaseBackupServlet {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    IdatabaseBackupDao dao=ctx.getBean(IdatabaseBackupDao.class);
    //数据库备份
    @RequestMapping(value = "/databaseBackup",method = RequestMethod.POST)
    @ResponseBody
    public void databaseBackup(HttpServletResponse response, HttpServletRequest request) throws InterruptedException, IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        //获取当前时间，给文件命名
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH时mm分ss秒");//设置日期格式
        String dftime=df.format(new Date());
        String hostIP="127.0.0.1";String userName="root";
        //获得本地路径
        String savePath=request.getSession().getServletContext().getRealPath("databaseBackup");
        String password="caoyu3520563253";
        String fileName=dftime+".sql";String databaseName="CakeDB3";
        if (backup(hostIP,userName,password,savePath,fileName,databaseName)) {
            DatabaseBackup databaseBackup=new DatabaseBackup();
            databaseBackup.setDpPath(savePath);databaseBackup.setDpFTime(new Date());
            if(dao.insertdatabaseBackup(databaseBackup)>0){
                response.getWriter().print("数据库备份成功!");
            }else {
                response.getWriter().print("数据库备份失败!");
            }
        } else {
            response.getWriter().print("数据库备份失败!");
        }
    }
    //查询备份数据库
    @RequestMapping(value = "/selectdatabaseBackup",method = RequestMethod.POST)
    @ResponseBody
    public List<DatabaseBackup> selectdatabaseBackup(@RequestBody List<Object> objectList){
        Map<String,Object> map=new HashMap<>();
        Object page=objectList.get(0);Object limit=objectList.get(1);
        int pages=(int)page;int limits=(int)limit;
        map.put("page",(pages-1)*limits);
        map.put("limit",limits);
        return dao.selectdatabaseBackup(map);
    }
    //查询数量
    @RequestMapping(value = "/selectdatabaseCount",method = RequestMethod.POST)
    @ResponseBody
    public int selectdatabaseCount(){
        return dao.selectdatabaseCount();
    }
    //备份删除
    @RequestMapping(value = "/deletedatabaseBackup",method = RequestMethod.POST)
    @ResponseBody
    public void deletedatabaseBackup(HttpServletRequest request,HttpServletResponse response) throws IOException {
        /*解决服务端的乱码问题*/
        response.setCharacterEncoding("utf-8");
        int dpId=Integer.parseInt(request.getParameter("dpId"));
        if(dao.deletedatabaseBackup(dpId)>0){
            response.getWriter().print("删除备份成功!");
        }else{
            response.getWriter().print("删除备份失败!");
        }
    }
    //查询迁移记录表中的数据
    @RequestMapping(value = "/selectHistoryShoppingDiary",method = RequestMethod.POST)
    @ResponseBody
    public List<HistoryShoppingDiary> selectHistoryShoppingDiary(@RequestBody List<Integer> integerList){
        Integer page=integerList.get(0);Integer limit=integerList.get(1);
        Integer pages=page-1;Integer limits=page*limit;
        Map<String,Integer> map=new HashMap<>();
        map.put("page",pages);map.put("limit",limits);
        return dao.selectHistoryShoppingDiary(map);
    }
    //进行迁移
    @RequestMapping(value = "/insertHistoryShoppingDiary",method = RequestMethod.POST)
    @ResponseBody
    public void insertHistoryShoppingDiary(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        HistoryShoppingDiary historyShoppingDiary=new HistoryShoppingDiary();
        //获得迁移数量
        int htCount=dao.selectShoppingStateCount();
        //添加数据到迁移表中
        dao.insertShoppingSidIn();
        //删除迁移的数据
        dao.deleteShoppingSidIn();
        //获得管理员姓名
        //设置sessionName值(用户名)
        HttpSession session=request.getSession();
        String admir=(String)session.getAttribute("sessionName");
        historyShoppingDiary.setHtCount(htCount);historyShoppingDiary.setHtState("迁移成功！");
        historyShoppingDiary.setHtAdmir(admir);
        if(dao.insertHistoryShoppingDiary(historyShoppingDiary)>0){
            response.getWriter().print("迁移成功!");
        }else{
            response.getWriter().print("迁移失败!");
        }
    }
    //查询迁移数量
    @RequestMapping(value = "/selectShoppingStateCount",method = RequestMethod.POST)
    @ResponseBody
    public int selectShoppingStateCount(){
        int count=dao.selectShoppingStateCount();
        return count;
    }
    //查询迁移记录表中的数量
    @RequestMapping(value = "/selectHistoryDiaryCount",method = RequestMethod.POST)
    @ResponseBody
    public int selectHistoryDiaryCount(){
        int count=dao.selectHistoryDiaryCount();
        return count;
    }
}
