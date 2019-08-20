package com.example.majiang.dto;

/***
 *
 * 获取access token的参数类
 */

public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redicret_uri;
    private String state;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedicret_uri() {
        return redicret_uri;
    }

    public void setRedicret_uri(String redicret_uri) {
        this.redicret_uri = redicret_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
