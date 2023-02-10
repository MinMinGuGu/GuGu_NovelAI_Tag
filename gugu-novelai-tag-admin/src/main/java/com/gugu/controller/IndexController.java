package com.gugu.controller;

import com.gugu.domain.Result;
import com.gugu.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The type Index controller.
 *
 * @author minmin
 * @date 2023 /02/09
 */
@Api("前端Index页相关接口")
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * Index string.
     *
     * @return the string
     */
    @GetMapping
    public String index() {
        return "index.html";
    }

    /**
     * Data result.
     *
     * @return the result
     */
    @GetMapping("/gugu_novel_ai_tag/index/data")
    @ResponseBody
    @ApiOperation("获取全部元素数据")
    public Result<?> data() {
        return Result.fastSuccess(indexService.getAllData());
    }

    /**
     * Test result.
     *
     * @return the result
     */
    @GetMapping("/gugu_novel_ai_tag/index/test")
    @ResponseBody
    @ApiOperation("测试接口")
    public Result<?> test() {
        return Result.fastSuccess();
    }
}
