package com.example.majiang.controller;

import com.example.majiang.dto.AccessTokenDTO;
import com.example.majiang.dto.GithubUser;
import com.example.majiang.mapper.UserMapper;
import com.example.majiang.model.User;
import com.example.majiang.provider.GithubProvider;
import com.example.majiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Authorize {

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

//    @ResponseBody
    @RequestMapping("/callback")
    public String AuthCallback(
            @RequestParam(name="code" ) String code,
            @RequestParam(name="state") String state,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        if(StringUtils.isEmpty(code)){
            return "授权失败";
        }

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedicret_uri(redirect_uri);
        accessTokenDTO.setState(state);
        // 获取token
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        // 获取用户信息
        GithubUser user = githubProvider.getUser(accessToken);
        // 判断用户信息是否为空
        if (user != null) {
            System.out.println("id: "+ user.getId());
            // 判断用户是否已经存入数据库
            userService.UserAddOrUpdate(user);
            // 设置session （会在重启服务器的时候失去用户登录状态所以改用cookie）
//            request.getSession().setAttribute("user", user);
            // 设置cookie
            response.addCookie(new Cookie("id",String.valueOf(user.getId())));
            return "redirect:/";
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(
            HttpServletRequest request,
            HttpServletResponse response
    ){
         // 删除session
        request.getSession().removeAttribute("user");
        // 删除cookie
        Cookie cookie = new Cookie("id", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
