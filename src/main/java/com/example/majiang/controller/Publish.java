package com.example.majiang.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.majiang.mapper.QuestionMapper;
import com.example.majiang.model.Question;
import com.example.majiang.model.User;
import com.example.majiang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class Publish {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publishView(
            Model model
    ) {
        model.addAttribute("question", null);

        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String publishEdit(
            @PathVariable(name = "id") Integer id,
            Model model
    ) {
        Question question = questionMapper.getById(id);
        model.addAttribute("question", question);
        return "publish";
    }

    @ResponseBody
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "id", required = false) Integer id,
            HttpServletRequest request,
            Map<String, Object> map
    ) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            map.put("code", 401);
            map.put("msg", "用户未登录!");
            JSONObject jsonObj = new JSONObject(map);
            return jsonObj.toString();
        }

        System.out.println("title: " + title + "\ndescription: " + description + "\ntag:" + tag + "\nid: " + id);
        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());

        questionService.addOrUpdate(question);

        map.put("code", 200);
        if (id != null) {
            map.put("msg", "修改成功!");
        } else {
            map.put("msg", "发布成功！");
        }
        JSONObject jsonObj = new JSONObject(map);
        return jsonObj.toString();
    }

}
