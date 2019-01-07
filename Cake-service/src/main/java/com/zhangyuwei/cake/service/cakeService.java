package com.zhangyuwei.cake.service;

import com.zhangyuwei.cake.dao.IcakeDao;
import com.zhangyuwei.cake.entities.RegsistAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cakeService implements IcakeService {
    @Autowired
    IcakeDao dao;
    //查询所有管理员
    public List<RegsistAdmin> selectAllRegsistAdmin(){
        return dao.selectAllRegsistAdmin();
    }

}
