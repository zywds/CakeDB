package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.NewUserInformation;
import com.zhangyuwei.cake.entities.Regsist;

import java.util.List;
import java.util.Map;

public interface IuserDao {
    //查询用户以及新增用户
    List<NewUserInformation> selectRegsistUserInformation(int rId);
    //查询所有用户
    List<Regsist> selectRegsist(Map<String,Object> map);
    //查询所有用户数量
    int selectRegsistCount(Regsist regsist);
}
