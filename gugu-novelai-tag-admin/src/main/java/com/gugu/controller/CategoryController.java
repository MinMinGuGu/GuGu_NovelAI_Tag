package com.gugu.controller;

import com.gugu.domain.Result;
import com.gugu.domain.entity.Category;
import com.gugu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author minmin
 * @since 2023 -02-09
 */
@RestController
@RequestMapping("/gugu_novel_ai_tag/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * Get result.
     *
     * @param search the search
     * @return the result
     */
    @GetMapping
    public Result<?> get(String search) {
        return Result.fastSuccess(categoryService.searchList(search));
    }

    /**
     * Post result.
     *
     * @param category the category
     * @return the result
     */
    @PostMapping
    public Result<?> post(@RequestBody Category category) {
        return categoryService.save(category) ? Result.fastSuccess() : Result.fastFail();
    }

    /**
     * Delete result.
     *
     * @param id the id
     * @return the result
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        return Result.fastSuccess(categoryService.removeCategory(id));
    }

    /**
     * Batch delete result.
     *
     * @param ids the ids
     * @return the result
     */
    @DeleteMapping("/delete")
    public Result<?> batchDelete(@RequestBody List<Integer> ids) {
        return Result.fastSuccess(categoryService.removeCategory(ids));
    }

    /**
     * Export.
     *
     * @param httpServletResponse the http servlet response
     */
    @GetMapping("/export")
    public void export(HttpServletResponse httpServletResponse) {
        categoryService.export(httpServletResponse);
    }
}
