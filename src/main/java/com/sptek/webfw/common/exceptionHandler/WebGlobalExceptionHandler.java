package com.sptek.webfw.common.exceptionHandler;

import com.sptek.webfw.common.exception.DuplicatedRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

//@Profile(value = { "notused" })
@Slf4j
@ControllerAdvice
public class WebGlobalExceptionHandler {



    //이전 request 응답하기 전 동일한 request 중복 요청했을때 DuplicateRequestPreventAspect 에서 발생시킴
    @ExceptionHandler(DuplicatedRequestException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public void handleDuplicatedRequestException(Exception ex) {
        log.error(ex.getMessage());
        //web 핸들러임에도 에러가 발생되는 케이스의 특성상 응답코드만 내리고 page는 내리지 않음
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoResourceFoundException(Exception ex) {
        log.error(ex.getMessage());
        return "error/commonInternalErrorView";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnExpectedException(Exception ex) {
        log.error(ex.getMessage());
        return "error/commonInternalErrorView";
    }

}
