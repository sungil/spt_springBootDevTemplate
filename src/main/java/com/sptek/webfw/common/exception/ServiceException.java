package com.sptek.webfw.common.exception;

import com.sptek.webfw.common.code.BaseCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceException extends Exception {
    private BaseCode serviceErrorCodeEnum;

    public ServiceException(BaseCode serviceErrorCodeEnum) {
        super(serviceErrorCodeEnum.getResultMessage());
        this.serviceErrorCodeEnum = serviceErrorCodeEnum;
    }

    public ServiceException(BaseCode serviceErrorCodeEnum, String exceptionMessage) {
        super(exceptionMessage);
        this.serviceErrorCodeEnum = serviceErrorCodeEnum;
    }
}