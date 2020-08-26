package com.zm.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ColumnEntity {

    @TableField("COLUMN_COMMENT")
    private String columnComment;

    @TableField("COLUMN_NAME")
    private String columnName;

    @TableField("DATA_TYPE")
    private String dataType;

    private String nullable;

    private Integer charLength;

    private String numLength;
}
