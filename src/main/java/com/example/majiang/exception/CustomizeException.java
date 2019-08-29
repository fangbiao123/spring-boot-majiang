package com.example.majiang.exception;

/***
 *
 * 自定义异常,
 * 在构造函数中传入枚举对象
 */
public class CustomizeException extends RuntimeException { // 需要继承runtimeException
    private String message;

    /**
     * 错误信息枚举类
     * @param errorCode
     */
    public CustomizeException(CoustomizeMessage errorCode){
        this.message = errorCode.getMessage();

    }

    /**
     * 错误信息自定义
     * @param message
     */
    public CustomizeException(String message){
        this.message = message;

    }

    @Override
    public String getMessage() {
        return message;
    }
}
