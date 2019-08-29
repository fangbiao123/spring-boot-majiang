package com.example.majiang.enums;

public enum CommentEnum {
    QUESTION(1),
    COMMENT(2);


    private Integer type;

    CommentEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    /**
     * 判断值对否存在于枚举中
     * @param type
     * @return
     */
    public static boolean exists(Integer type) {
        for (CommentEnum value : CommentEnum.values()) {
            if(value.getType() == type){
                return true;
            }
        }
        return false;
    }
}
