package com.example.majiang.controller;

import com.example.majiang.dto.PaginationDTO;
import com.example.majiang.model.User;
import com.example.majiang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String ProfileQuestion(
            HttpServletRequest request,
            @PathVariable(name="action") String action,
            @RequestParam(name="page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name="limit", required = false , defaultValue = "5") Integer limit,
            Model model
    ){

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        PaginationDTO pagination = questionService.list(user.getId(),page, limit);
        model.addAttribute("pagination", pagination);
        model.addAttribute("action", action);

//        String url = "";
//        url = request.getScheme() +"://" + request.getServerName()
//                + ":" +request.getServerPort()
//                + request.getServletPath();
//        if (request.getQueryString() != null){
//            url += "?" + request.getQueryString();
//        }
        model.addAttribute("servletPath", request.getServletPath());
        return "user/myQuestion";
    }
}
