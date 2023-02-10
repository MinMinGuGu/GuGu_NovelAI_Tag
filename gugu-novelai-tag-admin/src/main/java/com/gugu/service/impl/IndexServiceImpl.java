package com.gugu.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gugu.domain.entity.Attribute;
import com.gugu.domain.entity.Category;
import com.gugu.domain.pojo.CategoryAttr;
import com.gugu.mapper.AttributeMapper;
import com.gugu.mapper.CategoryMapper;
import com.gugu.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Index service.
 *
 * @author minmin
 * @date 2023 /02/09
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public List<CategoryAttr> getAllData() {
        List<Category> categoryList = categoryMapper.selectList(null);
        return categoryList.stream().map(item -> {
            String categoryName = item.getName();
            Attribute queryEntity = new Attribute();
            queryEntity.setCategoryId(item.getId());
            List<Attribute> attributeList = attributeMapper.selectList(Wrappers.query(queryEntity));
            return new CategoryAttr(categoryName, attributeList.stream().map(attribute -> {
                CategoryAttr.Attr attr = new CategoryAttr.Attr();
                attr.setName(attribute.getName());
                attr.setValue(attribute.getValue());
                return attr;
            }).collect(Collectors.toList()));
        }).collect(Collectors.toList());
    }
}
