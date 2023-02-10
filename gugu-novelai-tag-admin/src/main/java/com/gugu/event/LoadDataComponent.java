package com.gugu.event;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gugu.domain.entity.Attribute;
import com.gugu.domain.entity.Category;
import com.gugu.domain.entity.Config;
import com.gugu.domain.pojo.CategoryAttr;
import com.gugu.exception.LoadDataException;
import com.gugu.service.IAttributeService;
import com.gugu.service.ICategoryService;
import com.gugu.service.IConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * The type Auto load data task.
 *
 * @author minmin
 * @date 2023 /02/04
 */
@Slf4j
@Component
public class LoadDataComponent implements ApplicationListener<ApplicationReadyEvent> {

    private static final String LOAD_FLAG = "auto_load_data_flag";

    private static final String LOAD_DESCRIPTION = "初始化json数据成功标志";

    private static final String LOADED_VALUE = "success";

    private static final String LOAD_DATA = "data/data.json";

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private IAttributeService attributeService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IConfigService configService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Config config = configService.getConfigByKey(LOAD_FLAG);
        if (config != null && LOADED_VALUE.equals(config.getValue())) {
            return;
        }
        log.info("第一次运行 正在初始化标签数据至数据库中");
        TransactionStatus transaction = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(LoadDataComponent.LOAD_DATA);
            if (resourceAsStream == null) {
                throw
                        new LoadDataException("this.getClass().getClassLoader().getResourceAsStream(\"" + LoadDataComponent.LOAD_DATA + "\") == null");
            }

            load(resourceAsStream);
            log.info("标签数据已经初始化至数据库");
            Config sysConfig = new Config();
            sysConfig.setKey(LOAD_FLAG);
            sysConfig.setValue(LOADED_VALUE);
            sysConfig.setDescription(LOAD_DESCRIPTION);
            configService.save(sysConfig);
            platformTransactionManager.commit(transaction);
        } catch (Exception e) {
            log.error("标签数据没能初始化到数据库中", e);
            platformTransactionManager.rollback(transaction);
            throw new LoadDataException(e);
        }
    }

    /**
     * Load.
     *
     * @param inputStream the input stream
     * @throws IOException the io exception
     */
    public void load(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<CategoryAttr> categoryAttrList = objectMapper.readValue(inputStream, new TypeReference<List<CategoryAttr>>() {
        });
        for (CategoryAttr categoryAttr : categoryAttrList) {
            Category category = new Category();
            category.setName(categoryAttr.getCategory());
            categoryService.save(category);
            Category categoryByName = categoryService.getCategoryByName(categoryAttr.getCategory());
            List<CategoryAttr.Attr> attrList = categoryAttr.getAttrList();
            for (CategoryAttr.Attr attr : attrList) {
                Attribute attribute = new Attribute();
                attribute.setCategoryId(categoryByName.getId());
                attribute.setName(attr.getName());
                attribute.setValue(attr.getValue());
                attributeService.save(attribute);
            }
        }
    }
}
