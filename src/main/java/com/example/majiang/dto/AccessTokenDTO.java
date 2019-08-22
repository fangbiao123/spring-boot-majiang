package com.example.majiang.dto;

import lombok.Data;

/***
 *
 * 获取access token的参数类
 */

@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redicret_uri;
    private String state;
}
