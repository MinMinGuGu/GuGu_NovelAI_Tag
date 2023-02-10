package com.gugu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gugu.domain.entity.Category;
import com.gugu.domain.vo.AttributeVo;
import com.gugu.exception.ServiceException;
import com.gugu.mapper.AttributeMapper;
import com.gugu.mapper.CategoryMapper;
import com.gugu.service.ICategoryService;
import com.gugu.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The type Category service.
 *
 * @author minmin
 * @since 2023 -02-09
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    private final static String XLSX_NAME = "GuGu_NovelAI_Tag_Category.xlsx";

    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public Category getCategoryByName(String categoryName) {
        return this.getOne(this.query().eq("name", categoryName).getWrapper());
    }

    @Override
    public void export(HttpServletResponse httpServletResponse) {
        try {
            ResponseUtil.settingFileStreamHeader(httpServletResponse, XLSX_NAME);
            EasyExcel.write(httpServletResponse.getOutputStream(), AttributeVo.class).sheet("分类表").doWrite(this.list());
        } catch (IOException e) {
            log.error("获取响应流失败", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Category> searchList(String search) {
        return StringUtils.hasText(search) ? this.getBaseMapper().selectAllByField(search) : this.list();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean removeCategory(Integer id) {
        return attributeMapper.deleteByCategoryId(id) >= 0 & this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean removeCategory(List<Integer> ids) {
        return attributeMapper.deleteByCategoryIdList(ids) >= 0 & this.removeByIds(ids);
    }

    @Override
    public boolean save(Category entity) {
        Category category = this.getCategoryByName(entity.getName());
        return category == null && super.save(entity);
    }
}
