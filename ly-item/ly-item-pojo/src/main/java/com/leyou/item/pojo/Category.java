package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author LiuJinmai
 * @Description 商品分类信息实体类
 * @Date 2019/1/3 18:48
*/
@Data
@Table(name = "`tb_category`")
public class Category implements Serializable {
    /**
     * 类目id
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 类目名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 父类目id,顶级类目填0
     */
    @Column(name = "`parent_id`")
    private Long parentId;

    /**
     * 是否为父节点，0为否，1为是
     */
    @Column(name = "`is_parent`")
    private Boolean isParent;

    /**
     * 排序指数，越小越靠前
     */
    @Column(name = "`sort`")
    private Integer sort;
}