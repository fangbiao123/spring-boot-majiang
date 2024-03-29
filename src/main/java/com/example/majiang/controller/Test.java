package com.example.majiang.controller;

import com.example.majiang.mapper.UserMapper;
import com.example.majiang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test/")
public class Test {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/index")
    public String index(String name, Model model){
        System.out.println(System.currentTimeMillis()/1000);
        model.addAttribute("name", name);
        return "test/test";
    }

    @ResponseBody
    @GetMapping("/sql")
    public String sql(){
        User u = userMapper.getById(31725392);
        if(u != null){
            System.out.println("存在");
        }else{
            System.out.println("不存在！");
        }
        System.out.println(u.getAvatar_url());
        return "111";
    }
}
