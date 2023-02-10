package com.gugu;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;
import java.util.Collections;

/**
 * @author minmin
 * @date 2023/02/09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestWebApplication {

    @Autowired
    private Environment environment;

    @Test
    public void generate() {
        String runPath = System.getProperty("user.dir");
        String url = environment.getProperty("spring.datasource.url");
        String username = environment.getProperty("spring.datasource.username");
        String password = environment.getProperty("spring.datasource.password");
        assert url != null;
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("minmin") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(Paths.get(runPath, "mybatis-plus-generate").toString()); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.gugu") // 设置父包名
                            // .moduleName("gugu_novel_ai_tag") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, Paths.get(runPath, "mybatis-plus-generate", "mapper").toString())); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("gugu_attribute") // 设置需要生成的表名
                            .addInclude("gugu_category")
                            .addInclude("gugu_config")
                            .addTablePrefix("gugu_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
