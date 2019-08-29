package com.example.majiang.exception;

/**
 *
 * 错误提示枚举类
 */
public enum CoustomizeMessage {

    QUESTION_NOT_FOUND(2001,"问题不存在，或者已经删除！"),
    TARGET_PARENT_NOT_FOUND(2002, "未选择问题,或评论进行回复！"),
    NO_LOGIN(2003, "请先登录"),
    SYSTEM_ERROR(2004, "系统错误，请联系管理员！"),
    COMMENT_TYPE_ERROR(2005, "评论类型错误或不存在！"),
    COMMENT_CONTENT_ERROR(2006, "评论内容不能为空！"),
    COMMENT_QUESTION_ERROR(2007, "评论的问题不存在！"),
    COMMENT_REPLY_ERROR(2008, "回复的评论不存在!"),
    ;
    private Integer code;
    private String message;

    CoustomizeMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }}
