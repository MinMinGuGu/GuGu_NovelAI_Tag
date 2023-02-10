package com.gugu.controller;

import com.gugu.domain.Result;
import com.gugu.domain.entity.Attribute;
import com.gugu.service.IAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * The type Attribute controller.
 *
 * @author minmin
 * @since 2023 -02-09
 */
@Api("操作元素相关接口")
@RestController
@RequestMapping("/gugu_novel_ai_tag/attribute")
public class AttributeController {

    @Autowired
    private IAttributeService attributeService;

    /**
     * Get result.
     *
     * @param search the search
     * @return the result
     */
    @GetMapping
    @ApiOperation("获取元素数据")
    public Result<?> get(String search) {
        return Result.fastSuccess(attributeService.searchList(search));
    }

    /**
     * Post result.
     *
     * @param attribute the attribute
     * @return the result
     */
    @PostMapping
    @ApiOperation("新增元素数据")
    public Result<?> post(@RequestBody Attribute attribute) {
        return Result.fastSuccess(attributeService.save(attribute));
    }

    /**
     * Delete result.
     *
     * @param id the id
     * @return the result
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据元素id删除元素")
    public Result<?> delete(@PathVariable Integer id) {
        return Result.fastSuccess(attributeService.removeById(id));
    }

    /**
     * Batch delete result.
     *
     * @param ids the ids
     * @return the result
     */
    @DeleteMapping("/delete")
    @ApiOperation("根据元素id集合批量删除元素")
    public Result<?> batchDelete(@RequestBody List<Integer> ids) {
        return Result.fastSuccess(attributeService.removeBatchByIds(ids));
    }

    /**
     * Export.
     *
     * @param httpServletResponse the http servlet response
     */
    @GetMapping("/export")
    @ApiOperation("导出元素数据到表格中")
    public void export(HttpServletResponse httpServletResponse) {
        attributeService.export(httpServletResponse);
    }

    /**
     * Clean result.
     *
     * @return the result
     */
    @DeleteMapping("clean")
    @ApiOperation("清理元素数据中name和value重复的数据")
    public Result<?> clean() {
        attributeService.cleanData();
        return Result.fastSuccess();
    }
}
