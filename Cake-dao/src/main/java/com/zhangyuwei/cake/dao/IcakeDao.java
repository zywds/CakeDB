package com.zhangyuwei.cake.dao;
import com.zhangyuwei.cake.entities.RegsistAdmin;

import java.util.List;
import java.util.Map;
/**
 * 管理员信息
 * */
public interface IcakeDao {
    //查询所有管理员
    List<RegsistAdmin> selectAllRegsistAdmin();
    //登录(根据账号，密码查询管理员)
    List<RegsistAdmin> selectRegsistAdminByUserAndPassword(RegsistAdmin regsistAdmin);
    //查询，根据其权限进行查询
    List<RegsistAdmin> selectRegsistAdminByRole(RegsistAdmin regsistAdmin);
    //查询，分页
    List<RegsistAdmin> selectAdminByPage(Map<String,Object> objectMap);
    //查询数量
    int selectAdminCount(RegsistAdmin regsistAdmin);
    //注销管理员
    int updateState(RegsistAdmin regsistAdmin);
    //添加管理员
    int insertRegsistAdmin(RegsistAdmin regsistAdmin);
    //修改管理员
    int updateRegsistAdmin(RegsistAdmin regsistAdmin);
    //多项重置密码
    int updateRegsistAdminPassword(List<Integer> raIds);
    //判断登录名是否被注册
    int selectAdminByNameIfRegsist(RegsistAdmin regsistAdmin);
    //根据名字查找地位
    List<RegsistAdmin> selectAdminByNameOfRole(RegsistAdmin regsistAdmin);
}
