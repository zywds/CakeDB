package com.zhangyuwei.cake.service;

import com.zhangyuwei.cake.entities.RegsistAdmin;

import java.util.List;

public interface IcakeService {
    //查询所有管理员
    List<RegsistAdmin> selectAllRegsistAdmin();
}
