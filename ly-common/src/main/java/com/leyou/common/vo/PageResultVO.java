package com.leyou.common.vo;

import lombok.*;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 封装需要分页查询页面需要的通用视图对象
 * @Date 2019/1/5 14:52
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PageResultVO<T> {
    /**
     * 数据总条数
     */
    private Long total;
    /**
     * 数据总页数
     */
    private Integer totalPage;
    /**
     * 当前页数据
     */
    private List<T> items;

    public PageResultVO(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

}
