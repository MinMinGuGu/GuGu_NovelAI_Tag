package com.gugu.controller;

import com.gugu.domain.Result;
import com.gugu.domain.entity.Config;
import com.gugu.service.IConfigService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The type Config controller.
 *
 * @author minmin
 * @since 2023 -02-09
 */
@Api("操作系统配置相关接口")
@RestController
@RequestMapping("/gugu_novel_ai_tag/config")
public class ConfigController {
    @Autowired
    private IConfigService configService;

    /**
     * Get result.
     *
     * @param search the search
     * @return the result
     */
    @GetMapping
    @ApiOperation("获取系统配置数据")
    public Result<?> get(String search) {
        return Result.fastSuccess(configService.searchList(search));
    }

    /**
     * Post result.
     *
     * @param config the config
     * @return the result
     */
    @PostMapping
    @ApiOperation("新增系统配置")
    public Result<?> post(@RequestBody Config config) {
        return configService.save(config) ? Result.fastSuccess() : Result.fastFail();
    }

    /**
     * Delete result.
     *
     * @param id the id
     * @return the result
     */
    @ApiOperation("根据id删除数据")
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        return Result.fastSuccess(configService.removeById(id));
    }

    /**
     * Batch delete result.
     *
     * @param ids the ids
     * @return the result
     */
    @DeleteMapping("/delete")
    @ApiOperation("根据id集合批量删除数据")
    public Result<?> batchDelete(@RequestBody List<Integer> ids) {
        return Result.fastSuccess(configService.removeByIds(ids));
    }

    /**
     * Export.
     *
     * @param httpServletResponse the http servlet response
     */
    @GetMapping("/export")
    @ApiOperation("导出系统数据")
    public void export(HttpServletResponse httpServletResponse) {
        configService.export(httpServletResponse);
    }

    /**
     * Import data result.
     *
     * @param file the file
     * @return the result
     */
    @PostMapping("/import")
    @ApiOperation("导入系统数据")
    public Result<?> importData(@RequestBody MultipartFile file) {
        return configService.importData(file) ? Result.fastSuccess() : Result.fastFail();
    }
}
