package com.gugu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gugu.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * The interface Category mapper.
 *
 * @author minmin
 * @since 2023 -02-09
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * Select all by field list.
     *
     * @param search the search
     * @return the list
     */
    List<Category> selectAllByField(String search);
}
