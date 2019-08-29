package com.example.majiang.model;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private String content;
    private long gmtCreate;
    private long gmtModified;
    private Integer likeCount;
}
