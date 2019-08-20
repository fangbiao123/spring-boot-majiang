package com.example.majiang.provider;

import com.alibaba.fastjson.JSON;
import com.example.majiang.dto.AccessTokenDTO;
import com.example.majiang.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/***
 * 获取github 的access请求类
 */
@Component   // 加上这个注解使用的时候不需要实例化, 控制反转和依赖注入，注册了个Bean
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        String url = "https://github.com/login/oauth/access_token";

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String response_str = response.body().string();
            System.out.println(response_str);
            String[] params = response_str.split("&");
            String access_token = params[0].split("=")[1];
//            getUser(access_token);
            System.out.println(access_token);
            return access_token;
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return null;

    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.github.com/user?access_token=" + accessToken;
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String response_str = response.body().string();
            System.out.println(response_str);
            GithubUser githubUser = JSON.parseObject(response_str, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
