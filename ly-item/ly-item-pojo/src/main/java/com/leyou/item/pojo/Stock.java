package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author LiuJinmai
 * @Description 库存，秒杀库存等信息
 * @Date 2019/1/8 21:10
 */
@Data
@Table(name = "`tb_stock`")
public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 库存对应的商品sku id
     */
    @Id
    @Column(name = "`sku_id`")
    private Long skuId;
    /**
     * 可秒杀库存
     */
    @Column(name = "`seckill_stock`")
    private Integer seckillStock;
    /**
     * 秒杀总数量
     */
    @Column(name = "`seckill_total`")
    private Integer seckillTotal;
    /**
     * 库存数量
     */
    @Column(name = "`stock`")
    private Integer stock;
}