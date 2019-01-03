package com.leyou.common.vo;

import com.leyou.common.exceptions.LyException;
import lombok.Getter;
import org.joda.time.DateTime;

/**
 * @Author LiuJinmai
 * @Description 异常信息的视图对象
 * @Date 2019/1/3 16:17
 */
@Getter
public class ExceptionVO {

    private Integer status;
    private String message;
    private String timestamp;

    public ExceptionVO(LyException e) {
        this.status = e.getStatus();
        this.message = e.getMessage();
        this.timestamp = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }
}
