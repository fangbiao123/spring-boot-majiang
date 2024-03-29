package com.example.majiang.controller;

import com.example.majiang.dto.QuestionDTO;
import com.example.majiang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionDetailController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(
            @PathVariable(name = "id") Integer id,
            Model model
    ){

        QuestionDTO byId = questionService.getById(id);
        model.addAttribute("question",byId);
        questionService.addView(byId.getId());

        return "questionDetail";
    }
}
