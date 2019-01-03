package com.leyou.common.exceptions;

import com.leyou.common.enums.ExceptionInfoEnum;
import lombok.Getter;

/**
 * @Author LiuJinmai
 * @Description 自定义统一的处理异常类
 * @Date 2019/1/3 15:44
 */
public class LyException extends RuntimeException {
    /**
     * 异常状态码
     */
    @Getter
    private int status;

    public LyException(ExceptionInfoEnum exceptionInfoEnum) {
        super(exceptionInfoEnum.getMessage());
        this.status = exceptionInfoEnum.getStatus();
    }

    public LyException(ExceptionInfoEnum exceptionInfoEnum, Throwable cause) {
        super(exceptionInfoEnum.getMessage(), cause);
        this.status = exceptionInfoEnum.getStatus();
    }
}
