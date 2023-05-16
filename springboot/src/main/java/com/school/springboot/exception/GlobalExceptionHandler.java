package com.school.springboot.exception;

import java.util.stream.Collectors;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.school.springboot.entity.tool.*;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.support.DefaultMessageSourceResolvable;

/**
 * The basic ExceptionHandler for all kinds of exceptions,currently all kinds of
 * exception
 * 
 * @author 86136
 * @version 0.1
 */
@SuppressWarnings(value = "all")
@RestControllerAdvice
// @ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GlobalExceptionHandler {

    @Autowired
    public Environment env;

    // MeilisearchException是所有meilisearch异常类的父类！
    @ExceptionHandler(value = MeilisearchException.class)
    public Result meiliSearchHandler(MeilisearchException exception) {
        return Result.fail(exception.getMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    public Result nullPointerHandler(NullPointerException exception) {
        return Result.fail("您可能没有登录，请先登录！");
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public Result unauthorizedHandler(UnauthorizedException exception) {
        return Result.fail("您没有权限使用此功能！");
    }

    @ExceptionHandler(value = UnauthenticatedException.class)
    public Result unauthenticatedHandler(UnauthenticatedException exception) {
        return Result.fail("您没有登录，请先登录！");
    }

    //好像要加一个没有权限异常的处理器
    @ExceptionHandler(value = AuthenticationException.class)
    public Result shiroLoginFailureHandler(AuthenticationException exception) {
        return Result.fail("登录失败，您的用户名或者密码可能有误！");
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public Result ConstraintViolationExceptionHandler(ConstraintViolationException exception) {
        return Result.fail(exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class,BindException.class})
    public Result getValidationResult(MethodArgumentNotValidException exception) {
        // 从异常对象中拿到ObjectError对象
        // ObjectError objectError = exception.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return Result.fail(exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
    }

    // @ExceptionHandler(value = Exception.class)
    // public Result allExceptionHandler(Exception exception) {
    //     return Result.fail(exception.getMessage());
    // }

}
