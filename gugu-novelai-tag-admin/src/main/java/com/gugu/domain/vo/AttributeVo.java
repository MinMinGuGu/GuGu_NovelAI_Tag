package com.gugu.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * The type Attribute vo.
 *
 * @author minmin
 * @date 2023 /02/09
 */
@Data
public class AttributeVo {
    @ExcelProperty("id")
    private Integer id;
    @ExcelProperty("分类id")
    private Integer categoryId;
    @ExcelProperty("分类名")
    private String categoryName;
    @ExcelProperty("元素名")
    private String name;
    @ExcelProperty("元素值")
    private String value;
}
