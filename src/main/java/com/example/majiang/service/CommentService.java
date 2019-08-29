package com.example.majiang.service;

import com.example.majiang.enums.CommentEnum;
import com.example.majiang.exception.CoustomizeMessage;
import com.example.majiang.exception.CustomizeException;
import com.example.majiang.mapper.CommentMapper;
import com.example.majiang.mapper.QuestionMapper;
import com.example.majiang.model.Comment;
import com.example.majiang.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public void insert(Comment comment){
        // 验证父级id
        if(comment.getParentId() == null || comment.getParentId() == 0 ){
            throw new CustomizeException(CoustomizeMessage.TARGET_PARENT_NOT_FOUND);
        }

        // 验证评论类型
        if(comment.getType() == null || !CommentEnum.exists(comment.getType())){
            throw new CustomizeException(CoustomizeMessage.COMMENT_TYPE_ERROR);
        }

        // 验证评论内容
        if(comment.getContent() == null || comment.getContent() == ""){
            throw new CustomizeException(CoustomizeMessage.COMMENT_CONTENT_ERROR);
        }

        // 验证评论对象是否存在
        if(comment.getType() == CommentEnum.QUESTION.getType()){

            Question question = questionMapper.getById(comment.getParentId());
            if(question == null){
                throw new CustomizeException(CoustomizeMessage.COMMENT_QUESTION_ERROR);
            }
            questionMapper.addCommentCount(comment.getParentId());

        }else if(comment.getType() == CommentEnum.COMMENT.getType()){

            Comment queryComment = commentMapper.getByIdAndType(comment.getParentId(), CommentEnum.QUESTION.getType());
            if(queryComment == null){
                throw new CustomizeException(CoustomizeMessage.COMMENT_REPLY_ERROR);
            }

        }

        int id = commentMapper.insert(comment);
        System.out.println("插入返回的id : " + id);

    }
}
