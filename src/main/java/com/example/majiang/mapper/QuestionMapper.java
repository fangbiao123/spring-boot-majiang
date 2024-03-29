package com.example.majiang.mapper;

import com.example.majiang.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface QuestionMapper {
    @Insert("insert into question(title, description, tag,creator, gmtCreate, gmtModified) value (#{title}, #{description}, #{tag}, #{creator},#{gmtCreate}, #{gmtModified})")
    void insertQuestion(Question question);

    @Select("select * from question limit #{offset},#{limit}")
    List<Question> list(Integer offset, Integer limit);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{userId} limit #{offset},#{limit}")
    List<Question> listByUserId(Integer userId, Integer offset, Integer limit);

    @Select("select count(1) from question where creator =  #{userId}")
    Integer countByUserId(Integer userId);

    @Select("select * from question where id =  #{id}")
    Question getById(Integer id);

    @Update("update question set title = #{title}, description = #{description}, tag = #{tag}, gmtModified = #{gmtModified} where id = #{id}")
    void update(Question question);

    @Update("update question set viewCount= viewCount + 1 where id = #{id}")
    void addViewCount(Integer id);

    @Update("update question set commentCount= commentCount + 1 where id = #{id}")
    void addCommentCount(Integer id);
}
