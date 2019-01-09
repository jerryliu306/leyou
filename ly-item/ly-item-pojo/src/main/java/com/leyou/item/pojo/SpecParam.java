package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author LiuJinmai
 * @Description 商品信息的规格参数的参数名称实体类
 * @Date 2019/1/7 17:51
 */
@Data
@Table(name = "`tb_spec_param`")
public class SpecParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
     * 商品分类id
     */
    @Column(name = "`cid`")
    private Long cid;
    @Column(name = "`group_id`")
    private Long groupId;
    /**
     * 参数名
     */
    @Column(name = "`name`")
    private String name;
    /**
     * 是否是数字类型参数，true或false
     */
    @Column(name = "`numeric`")
    private Boolean numeric;
    /**
     * 数字类型参数的单位，非数字类型可以为空
     */
    @Column(name = "`unit`")
    private String unit;
    /**
     * 是否是sku通用属性，true或false
     */
    @Column(name = "`generic`")
    private Boolean generic;
    /**
     * 是否用于搜索过滤，true或false
     */
    @Column(name = "`searching`")
    private Boolean searching;
    /**
     * 数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0
     */
    @Column(name = "`segments`")
    private String segments;
}