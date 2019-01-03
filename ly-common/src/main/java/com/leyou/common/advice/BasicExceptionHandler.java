package com.leyou.common.advice;

import com.leyou.common.exceptions.LyException;
import com.leyou.common.vo.ExceptionVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author LiuJinmai
 * @Description 拦截所有加了{@Controller}的类，在这里进行返回异常信息
 * @Date 2019/1/3 15:28
 */
@ControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionVO> handlerException(LyException e){
        return ResponseEntity.status(e.getStatus()).body(new ExceptionVO(e));
    }
}
