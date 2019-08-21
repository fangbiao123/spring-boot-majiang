package com.example.majiang.controller;

import com.example.majiang.dto.AccessTokenDTO;
import com.example.majiang.dto.GithubUser;
import com.example.majiang.mapper.UserMapper;
import com.example.majiang.model.User;
import com.example.majiang.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
            // 查询用户是否已经存入数据库
            User u = userMapper.getById(user.getId());
            if(u == null){
                // 插入数据库
                User user1 = new User();
                user1.setAvatar_url(user.getAvatar_url());
                user1.setId(user.getId());
                user1.setLogin(user.getLogin());
                user1.setName(user.getName());
                userMapper.insert(user1);
            }else{
                System.out.println("已经有用户信息！");
            }

            // 设置session （会在重启服务器的时候失去用户登录状态所以改用cookie）
//            request.getSession().setAttribute("user", user);
            // 设置cookie
            response.addCookie(new Cookie("id",String.valueOf(user.getId())));
            return "redirect:/";
        }

        return "redirect:/";
    }
}
