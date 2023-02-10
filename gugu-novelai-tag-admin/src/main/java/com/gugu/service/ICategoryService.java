package com.gugu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gugu.domain.entity.Category;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The interface Category service.
 *
 * @author minmin
 * @since 2023 -02-09
 */
public interface ICategoryService extends IService<Category> {

    /**
     * Gets category by name.
     *
     * @param category the category
     * @return the category by name
     */
    Category getCategoryByName(String category);

    /**
     * Export.
     *
     * @param httpServletResponse the http servlet response
     */
    void export(HttpServletResponse httpServletResponse);

    /**
     * Search list list.
     *
     * @param search the search
     * @return the list
     */
    List<Category> searchList(String search);

    /**
     * Remove category boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean removeCategory(Integer id);

    /**
     * Remove category boolean.
     *
     * @param ids the ids
     * @return the boolean
     */
    boolean removeCategory(List<Integer> ids);
}
