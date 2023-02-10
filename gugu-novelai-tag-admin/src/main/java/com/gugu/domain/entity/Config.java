package com.gugu.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * The type Config.
 *
 * @author minmin
 * @since 2023 -02-09
 */
@Data
@TableName("GUGU_CONFIG")
@ApiModel(value = "Config对象", description = "")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    private String key;
    private String value;
    private String description;
}
