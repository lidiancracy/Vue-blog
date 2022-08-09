package com.sangeng.vo;

import lombok.Data;

/**
 * @ClassName HotArticlevo
 * @Description vo是我们希望在前端展示的数据，vo和entity有很多重复属性，我们可以用Bean赋值操作
 * @Date 2022/8/9 20:10
 */
@Data
public class HotArticlevo {
    private Long id;
    private String title;
    private Long viewCount;
}
