package com.wzy.common.security.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.wzy.common.core.domain.R;
import com.wzy.common.core.enums.ResultCode;
import com.wzy.common.security.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{
    /**
     * 请求⽅式不⽀持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<?>
    handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不⽀持'{}'请求", requestURI, e.getMethod());
        return R.fail(ResultCode.ERROR);
    }
    /**
     * 拦截运⾏时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R<?> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发⽣异常.", requestURI, e.getMessage());
        return R.fail(ResultCode.ERROR);
    }


    @ExceptionHandler(BindException.class)
    public R<Void> handleBindException(BindException e) {
        log.error(e.getMessage());
        String message = join(e.getAllErrors(), DefaultMessageSourceResolvable::getDefaultMessage, ", ");
        return R.fail(ResultCode.FAILED_PARAMS_VALIDATE.getCode(), message);
    }
    private <E> String join(Collection<E> collection, Function<E, String> function, CharSequence delimiter) {
        if (CollUtil.isEmpty(collection)) {
            return StrUtil.EMPTY;
        }
        return collection.stream().map(function).filter(Objects::nonNull).collect(Collectors.joining(delimiter));
    }
    /**
     *业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public R<?> handleServiceException(ServiceException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发⽣业务异常.", requestURI, e);
        return R.fail(e.getResultCode());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public R<?> handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发⽣异常.", requestURI, e);
        return R.fail(ResultCode.ERROR);
    }
}
