package com.gugu.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type Web config.
 *
 * @author minmin
 * @date 2023 /02/09
 */
@Configuration
public class WebConfig {
    /**
     * H 2 servlet registration bean servlet registration bean.
     *
     * @return the servlet registration bean
     */
    @Bean
    public ServletRegistrationBean<WebServlet> h2ServletRegistrationBean() {
        ServletRegistrationBean<WebServlet> h2ServletRegistrationBean = new ServletRegistrationBean<>();
        h2ServletRegistrationBean.setServlet(new WebServlet());
        h2ServletRegistrationBean.addUrlMappings("/h2-console/*");
        return h2ServletRegistrationBean;
    }

    /**
     * Cors mvc config cors mvc config.
     *
     * @return the cors mvc config
     */
    @Bean
    public GuGuMvcConfig corsMvcConfig() {
        return new GuGuMvcConfig();
    }


    private static class GuGuMvcConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods(
                            HttpMethod.GET.name(),
                            HttpMethod.POST.name(),
                            HttpMethod.DELETE.name()
                    );
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/**")
                    .addResourceLocations("classpath:/build/")
                    .addResourceLocations("classpath:META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/");
        }
    }
}
