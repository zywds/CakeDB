package com.zhangyuwei.cake.dao;

import com.zhangyuwei.cake.entities.DatabaseBackup;
import com.zhangyuwei.cake.entities.HistoryShoppingDiary;

import java.util.List;
import java.util.Map;

public interface IdatabaseBackupDao {
    //备份数据
    int insertdatabaseBackup(DatabaseBackup databaseBackup);
    //查询备份数据
    List<DatabaseBackup> selectdatabaseBackup(Map<String,Object> map);
    //查询数量
    int selectdatabaseCount();
    //删除备份
    int deletedatabaseBackup(int dpId);
    //查询购物车表中状态为0的数据的总数
    int selectShoppingStateCount();
    //删除购物车中被迁移的数据
    int deleteShoppingSidIn();
    //添加数据到迁移表
    int insertShoppingSidIn();
    //添加数据到迁移记录表
    int insertHistoryShoppingDiary(HistoryShoppingDiary historyShoppingDiary);
    //查询迁移记录表中的数据
    List<HistoryShoppingDiary> selectHistoryShoppingDiary(Map<String,Integer> map);
    //查询迁移记录表中的数量
    int selectHistoryDiaryCount();
}
