package com.zm.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class TableEntity {

    @TableField("TABLE_NAME")
    private String tableName;

    @TableField("TABLE_COMMENT")
    private String tableComment;
}
