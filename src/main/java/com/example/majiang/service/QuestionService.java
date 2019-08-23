package com.example.majiang.service;

import com.example.majiang.dto.PaginationDTO;
import com.example.majiang.dto.QuestionDTO;
import com.example.majiang.mapper.QuestionMapper;
import com.example.majiang.mapper.UserMapper;
import com.example.majiang.model.Question;
import com.example.majiang.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer limit) {


        // 问题总数量
        Integer total = questionMapper.count();

        PaginationDTO paginationDTO = new PaginationDTO();

        paginationDTO.setPagination(page, limit, total);
        // 超出最大页数或者最小页数
        page = page < 1 ? 1 : page;
        page = page > paginationDTO.getTotalPage() ? paginationDTO.getTotalPage() : page;

        // 计算offset
        Integer offset = limit * (page - 1);

        // 问题列表
        List<Question> Questions = questionMapper.list(offset, limit);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : Questions) {
            // 循环每条 查询用户
            User user = userMapper.getById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            // 数据copy到DTo
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setAvatar_url(user.getAvatar_url());
            // 最后添加到列表中
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);


        return paginationDTO;
    }
}
