package com.lot.config.exception;

/**
 * 继承RuntiomException（运行时的异常处理）
 * 定义一个可手动抛出异常的类
 * */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
