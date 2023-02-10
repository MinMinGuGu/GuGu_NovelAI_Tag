package com.gugu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Swagger config.
 *
 * @author minmin
 * @version 1.0
 * @since 1.8
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String VERSION = "1.0.0";

    /**
     * File docket.
     *
     * @return the docket
     */
    @Bean
    public Docket attribute() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Attribute")
                .apiInfo(getAttributeApiInfo())
                .select()
                .paths(PathSelectors.regex("/api/gugu_novel_ai_tag/attribute.*"))
                .build();
    }

    private ApiInfo getAttributeApiInfo() {
        return new ApiInfoBuilder()
                .title("Attribute Api")
                .description("元素接口")
                .version(VERSION)
                .build();
    }

    /**
     * System docket.
     *
     * @return the docket
     */
    @Bean
    public Docket category() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Category")
                .apiInfo(getCategoryApiInfo())
                .select()
                .paths(PathSelectors.regex("/api/gugu_novel_ai_tag/category.*"))
                .build();
    }

    private ApiInfo getCategoryApiInfo() {
        return new ApiInfoBuilder()
                .title("Category Api")
                .description("分类接口")
                .version(VERSION)
                .build();
    }

    /**
     * Config docket.
     *
     * @return the docket
     */
    @Bean
    public Docket config() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Config")
                .apiInfo(getConfigApiInfo())
                .select()
                .paths(PathSelectors.regex("/api/gugu_novel_ai_tag/config.*"))
                .build();
    }

    private ApiInfo getConfigApiInfo() {
        return new ApiInfoBuilder()
                .title("Config Api")
                .description("系统配置接口")
                .version(VERSION)
                .build();
    }

    /**
     * Index docket.
     *
     * @return the docket
     */
    @Bean
    public Docket index() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Index")
                .apiInfo(getConfigApiInfo())
                .select()
                .paths(PathSelectors.regex("/api/gugu_novel_ai_tag/index.*"))
                .build();
    }

    private ApiInfo getIndexApiInfo() {
        return new ApiInfoBuilder()
                .title("Index Controller")
                .description("系统配置接口")
                .version(VERSION)
                .build();
    }
}
