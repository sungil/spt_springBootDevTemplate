package com.sptek.webfw.common.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum SuccessCodeEnum implements BaseCode {
    DEFAULT_SUCCESS(HttpStatus.OK, "S000", "Success"),

    //아래 다른 sucessCode도 실제 쓸일이 있을까??
    SELECT_SUCCESS(HttpStatus.OK, "S001", "Select Success"),
    DELETE_SUCCESS(HttpStatus.OK, "S002", "Delete Success"),
    INSERT_SUCCESS(HttpStatus.OK, "S003", "Insert Success"),
    UPDATE_SUCCESS(HttpStatus.OK, "S004", "Update Success");


    private final HttpStatus httpStatusCode;
    private final String resultCode;
    private final String resultMessage;


    SuccessCodeEnum(final HttpStatus httpStatusCode, final String resultCode, final String resultMessage) {
        this.httpStatusCode = httpStatusCode;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
}
