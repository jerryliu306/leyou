package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @Author LiuJinmai
 * @Description 异常信息枚举类，包括：
 *              异常类型（异常状态码，异常信息）
 * @Date 2019/1/3 15:37
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionInfoEnum {
    //404
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "商品分类不存在"),
    BRAND_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "商品品牌不存在"),
    SPEC_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "商品规格参数分组不存在"),
    SPEC_PARAM_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "该商品规格分组内没有参数"),
    GOODS_SPU_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "没有商品列表"),
    GOODS_SPU_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "没有查询到该商品"),
    GOODS_SKU_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "没有查询到该商品Sku"),
    GOODS_SKU_STOCK_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "没有查询到该商品Stock"),
    //400
    BRAND_EDIT_ERROR(HttpStatus.BAD_REQUEST.value(), "参数有误"),
    IMAGE_TYPE_ERROR(HttpStatus.BAD_REQUEST.value(), "图片类型不正确"),
    GOODS_SAVE_ERROR_SPU(HttpStatus.BAD_REQUEST.value(), "保存商品（SPU）失败"),
    GOODS_SAVE_ERROR_SPU_DETAIL(HttpStatus.BAD_REQUEST.value(), "保存商品（SPU_DETAIL）失败"),
    GOODS_SAVE_ERROR_SKU(HttpStatus.BAD_REQUEST.value(), "保存商品（SKU）失败"),
    GOODS_SAVE_ERROR_SKU_STOCK(HttpStatus.BAD_REQUEST.value(), "保存商品（SKU_STOCK）失败"),
    GOODS_ID_CANNOT_BE_NULL(HttpStatus.BAD_REQUEST.value(), "修改商品-商品Id为空"),
    //417
    FILE_UPLOAD_ERROR(HttpStatus.EXPECTATION_FAILED.value(), "上传图片失败"),
    GOODS_SALEABLE_EDIT_ERROR(HttpStatus.EXPECTATION_FAILED.value(), "修改上下架失败"),
    DELETE_SKU_FAIL(HttpStatus.EXPECTATION_FAILED.value(), "删除SKU失败"),
    DELETE_STOCK_FAIL(HttpStatus.EXPECTATION_FAILED.value(), "删除STOCK失败"),
    UPDATE_SPU_FAIL(HttpStatus.EXPECTATION_FAILED.value(), "修改SPU失败"),
    UPDATE_SPU_DETAIL_FAIL(HttpStatus.EXPECTATION_FAILED.value(), "修改DETAIL失败");
    /**
     * 异常状态码
     */
    private int status;
    /**
     * 异常信息
     */
    private String message;

    /**
     * 获得当前异常状态码
     * @return 状态码
     */
    public int getStatus(){
        return this.status;
    }

    /**
     * 或得异常当前状态信息
     * @return 异常信息
     */
    public String getMessage(){
        return this.message;
    }

}
