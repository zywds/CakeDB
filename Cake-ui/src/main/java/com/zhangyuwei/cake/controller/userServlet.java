package com.zhangyuwei.cake.controller;

import com.zhangyuwei.cake.dao.IcakeDao;
import com.zhangyuwei.cake.dao.IuserDao;
import com.zhangyuwei.cake.entities.Regsist;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("X-admin/UserServlet")
public class userServlet {
    ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
    IuserDao dao=ctx.getBean(IuserDao.class);
    @RequestMapping("/selectRegsist")
    @ResponseBody
    public List<Regsist> selectRegsist(@RequestBody List<Object> objectList){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("rPhone",objectList.get(2));
        Object page=objectList.get(0);Object limit=objectList.get(1);
        int pages=(int)page;int limits=(int)limit;
        map.put("page",(pages-1)*limits);
        map.put("limit",limits);
        return dao.selectRegsist(map);
    }
    @RequestMapping("/selectRegsistCount")
    @ResponseBody
    public int selectRegsistCount(HttpServletRequest request){
        Regsist regsist=new Regsist();
        regsist.setrPhone(request.getParameter("rPhone"));
        return dao.selectRegsistCount(regsist);
    }
}
