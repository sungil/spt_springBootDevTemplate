package com.sptek.webfw.common.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ServiceErrorCodeEnum implements BaseCode {

    SERVICE_DUPLICATION_REQUEST_ERROR(HttpStatus.TOO_MANY_REQUESTS, "SE429", "Duplication Request Exception"),


    //todo: SERVICE_DEFAULT_ERROR를 발생시킨 경우 HttpStatus를 뭐로 주는게 좋을까? 200일까? 고민해 봐야함
    SERVICE_DEFAULT_ERROR(HttpStatus.BAD_REQUEST, "SE000", "Default Error Exception"),

    //example
    SERVICE_001_ERROR(HttpStatus.CONFLICT, "SE001", "중복된 아이디 입니다."),
    SERVICE_002_ERROR(HttpStatus.NOT_FOUND, "SE002", "구매 이력이 없습니다.");


    private final HttpStatus httpStatusCode;
    private final String resultCode;
    private final String resultMessage;

    ServiceErrorCodeEnum(final HttpStatus httpStatusCode, final String resultCode, final String resultMessage) {
        this.httpStatusCode = httpStatusCode;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
}
