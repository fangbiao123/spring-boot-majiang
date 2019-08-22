package com.example.majiang.mapper;

import com.example.majiang.model.Question;
import org.apache.ibatis.annotations.Insert;

public interface QuestionMapper {
    @Insert("insert into question(title, description, tag,creator, gmtCreate, gmtModified) value (#{title}, #{description}, #{tag}, #{creator},#{gmtCreate}, #{gmtModified})")
    void insertQuestion(Question question);
}
