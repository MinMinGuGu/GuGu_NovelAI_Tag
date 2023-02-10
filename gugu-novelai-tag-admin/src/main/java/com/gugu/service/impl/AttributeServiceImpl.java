package com.gugu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gugu.domain.entity.Attribute;
import com.gugu.domain.entity.Category;
import com.gugu.domain.vo.AttributeVo;
import com.gugu.exception.ServiceException;
import com.gugu.mapper.AttributeMapper;
import com.gugu.mapper.CategoryMapper;
import com.gugu.service.IAttributeService;
import com.gugu.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Attribute service.
 *
 * @author minmin
 * @since 2023 -02-09
 */
@Service
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute> implements IAttributeService {

    private final static String XLSX_NAME = "GuGu_NovelAI_Tag_Attribute.xlsx";

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<AttributeVo> searchList(String search) {
        List<Attribute> attributes;
        if (StringUtils.hasText(search)) {
            attributes = this.getBaseMapper().selectAllByField(search);
        } else {
            attributes = this.list();
        }
        return attributes.stream().map(item -> {
            Category category = categoryMapper.selectById(item.getCategoryId());
            AttributeVo attributeVo = new AttributeVo();
            attributeVo.setId(item.getId());
            attributeVo.setCategoryId(item.getCategoryId());
            attributeVo.setName(item.getName());
            attributeVo.setValue(item.getValue());
            attributeVo.setCategoryName(category.getName());
            return attributeVo;
        }).collect(Collectors.toList());
    }

    @Override
    public void export(HttpServletResponse httpServletResponse) {
        try {
            ResponseUtil.settingFileStreamHeader(httpServletResponse, XLSX_NAME);
            EasyExcel.write(httpServletResponse.getOutputStream(), AttributeVo.class).sheet("元素表").doWrite(this.searchList(null));
        } catch (IOException e) {
            log.error("获取响应流失败", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void cleanData() {
        List<Attribute> attributeList = this.list();
        Set<Attribute> attributeSet = new LinkedHashSet<>(attributeList);
        Map<Integer, Attribute> attributeMap = attributeSet.stream().collect(Collectors.toMap(Attribute::getId, item -> item));
        List<Integer> idList = attributeList.stream().map(Attribute::getId).filter(id -> attributeMap.get(id) == null).collect(Collectors.toList());
        this.removeByIds(idList);
    }
}
