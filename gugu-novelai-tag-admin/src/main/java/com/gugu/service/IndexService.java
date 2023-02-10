package com.gugu.service;

import com.gugu.domain.pojo.CategoryAttr;

import java.util.List;

/**
 * The interface Index service.
 *
 * @author minmin
 * @date 2023 /02/09
 */
public interface IndexService {
    /**
     * Gets all data.
     *
     * @return the all data
     */
    List<CategoryAttr> getAllData();
}
