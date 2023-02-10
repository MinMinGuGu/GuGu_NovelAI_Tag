package com.gugu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gugu.domain.entity.Attribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface Attribute mapper.
 *
 * @author minmin
 * @since 2023 -02-09
 */
@Mapper
public interface AttributeMapper extends BaseMapper<Attribute> {
    /**
     * Select all by field list.
     *
     * @param value the value
     * @return the list
     */
    List<Attribute> selectAllByField(String value);

    /**
     * Delete by category id int.
     *
     * @param id the id
     * @return the int
     */
    int deleteByCategoryId(Integer id);

    /**
     * Delete by category id list int.
     *
     * @param categoryList the category list
     * @return the int
     */
    int deleteByCategoryIdList(@Param("categoryList") List<Integer> categoryList);
}
