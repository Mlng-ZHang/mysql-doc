package com.zm.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zm.demo.entity.ColumnEntity;
import com.zm.demo.entity.TableEntity;

import java.util.List;

public interface DbMapper extends BaseMapper {

    /**
     * 获取指定db下的所有表名和注释
     * @param dbName
     * @return
     */
    List<TableEntity> getAllTables(String dbName);

    /**
     * 获取指定db和table下所有字段信息
     * @param dbName
     * @param tableName
     * @return
     */
    List<ColumnEntity> getAllFields(String dbName, String tableName);
}
