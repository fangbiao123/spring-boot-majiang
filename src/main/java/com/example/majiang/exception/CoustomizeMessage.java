package com.example.majiang.exception;

/**
 *
 * 错误提示枚举类
 */
public enum CoustomizeMessage {

    QUESTION_NOT_FOUND("问题不存在，或者已经删除！");
    private String message;

    /**
     *
     * @param message
     */
    CoustomizeMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }



}
