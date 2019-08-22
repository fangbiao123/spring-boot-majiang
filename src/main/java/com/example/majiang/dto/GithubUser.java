package com.example.majiang.dto;

import lombok.Data;

@Data
public class GithubUser {
    private int id;
    private String login;
    private String name;
    private String avatar_url;

}
