package com.gugu.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * The type Category.
 *
 * @author minmin
 * @since 2023 -02-09
 */
@Data
@TableName("GUGU_CATEGORY")
@ApiModel(value = "Category对象", description = "")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    private String name;
}
