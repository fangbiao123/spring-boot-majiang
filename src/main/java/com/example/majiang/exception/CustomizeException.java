package com.example.majiang.exception;

/***
 *
 * 自定义异常,
 * 在构造函数中传入枚举对象
 */
public class CustomizeException extends RuntimeException { // 需要继承runtimeException
    private String message;
    private Integer code;

    /**
     * 错误信息枚举类
     * @param errorCode
     */
    public CustomizeException(CoustomizeMessage errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();

    }

    /**
     * 错误信息自定义
     * @param message
     */
    public CustomizeException(Integer code, String message){
        this.code = code;
        this.message = message;

    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
