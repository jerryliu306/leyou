package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author LiuJinmai
 * @Description 商品信息的规格参数的规格组实体类
 * 每个商品分类下有多个规格参数组
 * @Date 2019/1/7 14:29
 */
@Data
@Table(name = "`tb_spec_group`")
public class SpecGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
     * 商品分类id，一个分类下有多个规格组
     */
    @Column(name = "`cid`")
    private Long cid;
    /**
     * 规格组的名称
     */
    @Column(name = "`name`")
    private String name;
}