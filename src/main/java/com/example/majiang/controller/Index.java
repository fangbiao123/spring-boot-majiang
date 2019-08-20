package com.example.majiang.controller;

import com.example.majiang.dto.AccessTokenDTO;
import com.example.majiang.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class Index {
    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @RequestMapping("/")
    public String index(Map<String, Object> map){
        map.put("client_id",client_id);
        map.put("redirect_url", redirect_uri);
        map.put("scope", "user");
        map.put("state", "12312312");
//        System.out.println(map);
        return "index";
    }

    @Autowired
    private GithubProvider githubProvider;


    @ResponseBody
    @RequestMapping("/callback")
    public String AuthCallback(@RequestParam(name="code" ) String code, @RequestParam(name="state") String state){
        if(StringUtils.isEmpty(code)){
            return "授权失败";
        }
        String callback_code = "code:"+ code + "\nstate:" + state;

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedicret_uri(redirect_uri);
        accessTokenDTO.setState(state);
        githubProvider.getAccessToken(accessTokenDTO);
        return callback_code;
    }
}
