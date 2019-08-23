package com.example.majiang.mapper;

import com.example.majiang.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

public interface QuestionMapper {
    @Insert("insert into question(title, description, tag,creator, gmtCreate, gmtModified) value (#{title}, #{description}, #{tag}, #{creator},#{gmtCreate}, #{gmtModified})")
    void insertQuestion(Question question);

    @Select("select * from question limit #{offset},#{limit}")
    List<Question> list(Integer offset, Integer limit);

    @Select("select count(1) from question")
    Integer count();

}
