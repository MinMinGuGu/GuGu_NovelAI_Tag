package com.gugu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gugu.domain.entity.Config;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The interface Config service.
 *
 * @author minmin
 * @since 2023 -02-09
 */
public interface IConfigService extends IService<Config> {

    /**
     * Gets config by key.
     *
     * @param loadFlag the load flag
     * @return the config by key
     */
    Config getConfigByKey(String loadFlag);

    /**
     * Search list list.
     *
     * @param search the search
     * @return the list
     */
    List<Config> searchList(String search);

    /**
     * Export.
     *
     * @param httpServletResponse the http servlet response
     */
    void export(HttpServletResponse httpServletResponse);

    /**
     * Import data boolean.
     *
     * @param multipartFile the multipart file
     * @return the boolean
     */
    boolean importData(MultipartFile multipartFile);
}
