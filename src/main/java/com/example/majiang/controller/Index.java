package com.example.majiang.controller;

import com.example.majiang.dto.AccessTokenDTO;
import com.example.majiang.dto.GithubUser;
import com.example.majiang.mapper.UserMapper;
import com.example.majiang.model.User;
import com.example.majiang.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private UserMapper userMapper;


    @RequestMapping("/")
    public String index(Map<String, Object> map, HttpServletRequest request){
        // 获取cookie
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0) {
            // 遍历cookie
            for(Cookie cookie : cookies){
                // 找到对应的key
                System.out.println(cookie.getName());
                if(cookie.getName().equals("id")){
                    System.out.println("get user ");
                    String id = cookie.getValue();
                    // 查询数据库是否有这个id
                    User user = userMapper.getById(Integer.parseInt(id));
                    if(user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        map.put("client_id",client_id);
        map.put("redirect_url", redirect_uri);
        map.put("scope", "user");
        map.put("state", "12312312");
//        System.out.println(map);
        return "index";
    }





}
