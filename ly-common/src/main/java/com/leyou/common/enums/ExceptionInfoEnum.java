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
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"商品分类不存在");

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
