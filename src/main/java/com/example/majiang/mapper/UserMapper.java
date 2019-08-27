package com.example.majiang.mapper;

import com.example.majiang.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Insert("insert into github_user(id, login_name, avatar_url, name) value (#{id}, #{login}, #{avatar_url}, #{name})")
    void insert(User user);

    @Select("select * from github_user where id = #{id}")
    User getById(int id);

    @Update("update github_user set avatar_url=#{avatar_url}, name=#{name} where id = #{id}")
    void update(User user);
}
