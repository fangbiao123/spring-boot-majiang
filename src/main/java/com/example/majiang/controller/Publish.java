package com.example.majiang.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.majiang.mapper.QuestionMapper;
import com.example.majiang.mapper.UserMapper;
import com.example.majiang.model.Question;
import com.example.majiang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class Publish {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publishView(
            HttpServletRequest request
    ) {
        User user = null;
        // 获取cookie
        Cookie[] cookies = request.getCookies();
        // 遍历cookie
        if(cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                // 找到对应的key
                if (cookie.getName().equals("id")) {
                    String id = cookie.getValue();
                    // 查询数据库是否有这个id
                    user = userMapper.getById(Integer.parseInt(id));
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        return "publish";
    }

    @ResponseBody
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "tag", required = false) String tag,
            HttpServletRequest request,
            Map<String,Object> map
    ) {
        System.out.println("post publish");
        User user = null;
        // 获取cookie
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0) {
            // 遍历cookie
            for (Cookie cookie : cookies) {
                // 找到对应的key
                if (cookie.getName().equals("id")) {
                    String id = cookie.getValue();
                    // 查询数据库是否有这个id
                    user = userMapper.getById(Integer.parseInt(id));
                    break;
                }
            }
        }
        if(user == null){
            map.put("code", 401);
            map.put("msg", "用户未登录!");
            JSONObject jsonObj=new JSONObject(map);
            return jsonObj.toString();
        }

        System.out.println("title: " + title + "\ndescription: " + description + "\ntag:" + tag);
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());

        questionMapper.insertQuestion(question);
        map.put("code", 200);
        map.put("msg", "发布成功！");
        JSONObject jsonObj=new JSONObject(map);
        return jsonObj.toString();
    }

}
