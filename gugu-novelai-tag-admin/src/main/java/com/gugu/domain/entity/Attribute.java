package com.gugu.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Attribute.
 *
 * @author minmin
 * @since 2023 -02-09
 */
@Data
@TableName("GUGU_ATTRIBUTE")
@ApiModel(value = "Attribute对象", description = "")
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    private Integer categoryId;
    private String name;
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attribute attribute = (Attribute) o;
        return name.equals(attribute.name) && value.equals(attribute.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
