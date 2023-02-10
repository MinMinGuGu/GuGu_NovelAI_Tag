package com.gugu.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Category attr.
 *
 * @author minmin
 * @date 2023 /02/07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAttr {
    private String category;
    private List<Attr> attrList;

    /**
     * The type Attr.
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static public class Attr {
        private String name;
        private String value;
    }
}
