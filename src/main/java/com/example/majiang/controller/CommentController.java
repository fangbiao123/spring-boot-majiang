package com.example.majiang.controller;

import com.example.majiang.dto.CommentDTO;
import com.example.majiang.dto.ResultDTO;
import com.example.majiang.exception.CoustomizeMessage;
import com.example.majiang.model.Comment;
import com.example.majiang.model.User;
import com.example.majiang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @ResponseBody
    @PostMapping("/comment")
    public Object comment(
            @RequestBody CommentDTO commentDTO,  // 参数会自动对应到类中
            HttpServletRequest request
            ){
        User user = (User)request.getSession().getAttribute("user");
//        if(user == null) {
//            return ResultDTO.error(CoustomizeMessage.NO_LOGIN);
//        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setCommentator(31725392);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        commentService.insert(comment);

        return ResultDTO.success();
    }
}
