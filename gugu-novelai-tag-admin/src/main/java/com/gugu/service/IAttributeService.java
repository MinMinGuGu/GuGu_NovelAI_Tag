package com.gugu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gugu.domain.entity.Attribute;
import com.gugu.domain.vo.AttributeVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The interface Attribute service.
 *
 * @author minmin
 * @since 2023 -02-09
 */
public interface IAttributeService extends IService<Attribute> {

    /**
     * Search list list.
     *
     * @param search the search
     * @return the list
     */
    List<AttributeVo> searchList(String search);

    /**
     * Export.
     *
     * @param httpServletResponse the http servlet response
     */
    void export(HttpServletResponse httpServletResponse);

    /**
     * Clean data.
     */
    void cleanData();
}
