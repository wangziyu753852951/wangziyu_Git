package com.wzy.common.security.exception;

import com.wzy.common.core.enums.ResultCode;
import lombok.Getter;

/**
 * ClassName: ServiceException
 * Description
 *
 * @Author wzy
 * @Create 2025/8/3 10:12
 * @Version 1.0
 */
@Getter
public class ServiceException extends RuntimeException {

    private ResultCode resultCode;

    public ServiceException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}