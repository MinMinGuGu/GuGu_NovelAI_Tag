package com.gugu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gugu.domain.entity.Config;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * The interface Config mapper.
 *
 * @author minmin
 * @since 2023 -02-09
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

    /**
     * Select all by field list.
     *
     * @param search the search
     * @return the list
     */
    List<Config> selectAllByField(String search);
}
