package com.example.majiang.mapper;

import com.example.majiang.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface CommentMapper {


    @Select("select * from comment where id = #{id} and type = #{type}")
    Comment getByIdAndType(Integer id, Integer type);

    @Insert("insert into comment(parentId, type, commentator, content, gmtCreate, gmtModified) value (#{parentId}, #{type}, #{commentator}, #{content}, #{gmtCreate}, #{gmtModified})")
    Integer insert(Comment comment);
}
