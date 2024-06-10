package com.sptek.webfw.common.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum CommonErrorCodeEnum implements BaseCode {

    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "GE001", "BAD_REQUEST_ERROR"),
    INVALID_TYPE_VALUE_ERROR(HttpStatus.BAD_REQUEST, "GE002", "INVALID_TYPE_VALUE_ERROR"),
    MISSING_REQUEST_PARAMETER_ERROR(HttpStatus.BAD_REQUEST, "GE003", "MISSING_REQUEST_PARAMETER_ERROR"),
    IO_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GE004", "IO_ERROR"),
    REQUEST_BODY_NOT_READABLE_ERROR(HttpStatus.BAD_REQUEST, "GE005", "REQUEST_BODY_NOT_READABLE_ERROR"),
    JSON_PARSE_ERROR(HttpStatus.BAD_REQUEST, "GE006", "JSON_PARSE_ERROR"),
    JACKSON_PROCESS_ERROR(HttpStatus.BAD_REQUEST, "GE007", "JACKSON_PROCESS_ERROR"),
    FORBIDDEN_ERROR(HttpStatus.FORBIDDEN, "GE008", "FORBIDDEN_ERROR"),
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "GE009", "NOT_FOUND_ERROR"),
    NULL_POINT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GE010", "NULL_POINT_ERROR"),
    NOT_VALID_ERROR(HttpStatus.BAD_REQUEST, "GE011", "NOT_VALID_ERROR"),
    NOT_VALID_HEADER_ERROR(HttpStatus.BAD_REQUEST, "GE012", "NOT_VALID_HEADER_ERROR"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GE999", "INTERNAL_SERVER_ERROR");

    private final HttpStatus httpStatusCode;
    private final String resultCode;
    private final String resultMessage;

    CommonErrorCodeEnum(final HttpStatus httpStatusCode, final String resultCode, final String resultMessage) {
        this.httpStatusCode = httpStatusCode;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
}
