package com.example.majiang.controller;

import com.example.majiang.dto.PaginationDTO;
import com.example.majiang.exception.CoustomizeMessage;
import com.example.majiang.exception.CustomizeException;
import com.example.majiang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class Index {
    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect.uri}")
    private String redirect_uri;


    @Autowired
    private QuestionService questionService;


    @RequestMapping("/")
    public String index(
            @RequestParam(name="page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name="limit", required = false, defaultValue = "5") Integer limit,
            Map<String, Object> map,
            Model model
    ){


        map.put("client_id",client_id);
        map.put("redirect_url", redirect_uri);
        map.put("scope", "user");
        map.put("state", "12312312");
//        System.out.println(map);
        // 获取列表
        PaginationDTO pagination = questionService.list(page, limit);
        model.addAttribute("pagination",pagination);
//        if(map.get("client_id") != "123"){
//
//            throw new CustomizeException(CoustomizeMessage.SYSTEM_ERROR);
//        }
        return "index";
    }





}
