package com.gugu.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gugu.domain.entity.Attribute;
import com.gugu.domain.entity.Category;
import com.gugu.domain.entity.Config;
import com.gugu.domain.pojo.CategoryAttr;
import com.gugu.event.LoadDataComponent;
import com.gugu.exception.ServiceException;
import com.gugu.mapper.AttributeMapper;
import com.gugu.mapper.CategoryMapper;
import com.gugu.mapper.ConfigMapper;
import com.gugu.service.IConfigService;
import com.gugu.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Config service.
 *
 * @author minmin
 * @since 2023 -02-09
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

    private static final String FILE_NAME = "data.json";

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private LoadDataComponent loadDataComponent;

    @Override
    public Config getConfigByKey(String loadFlag) {
        return this.getOne(this.query().eq("key", loadFlag).getWrapper());
    }

    @Override
    public List<Config> searchList(String search) {
        return StringUtils.hasText(search) ? this.getBaseMapper().selectAllByField(search) : this.list();
    }

    @Override
    public void export(HttpServletResponse httpServletResponse) {
        try {
            ResponseUtil.settingFileStreamHeader(httpServletResponse, FILE_NAME);
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            List<CategoryAttr> categoryAttrList = this.readData2CategoryAttrList();
            outputStream.write(new ObjectMapper().writeValueAsBytes(categoryAttrList));
            outputStream.flush();
        } catch (IOException e) {
            log.error("获取响应流失败", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean importData(MultipartFile multipartFile) {
        try {
            loadDataComponent.load(multipartFile.getInputStream());
            return true;
        } catch (Exception e) {
            log.error("导入数据时异常", e);
        }
        return false;
    }

    private List<CategoryAttr> readData2CategoryAttrList() {
        List<Category> categoryList = categoryMapper.selectList(null);
        return categoryList.stream().map(item -> {
            Attribute queryEntity = new Attribute();
            queryEntity.setCategoryId(item.getId());
            List<Attribute> attributeList = attributeMapper.selectList(Wrappers.query(queryEntity));
            List<CategoryAttr.Attr> attrList = attributeList.stream().map(attribute -> new CategoryAttr.Attr(attribute.getName(), attribute.getValue())).collect(Collectors.toList());
            return new CategoryAttr(item.getName(), attrList);
        }).collect(Collectors.toList());
    }

    @Override
    public boolean save(Config entity) {
        Config config = this.getOne(this.query().eq("key", entity.getKey()).getWrapper());
        return config == null && super.save(entity);
    }
}
