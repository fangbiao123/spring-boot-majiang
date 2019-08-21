package com.example.majiang.mapper;

import com.example.majiang.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Insert("insert into github_user(id, login_name, avatar_url, name) value (#{id}, #{login}, #{avatar_url}, #{name})")
    void insert(User user);

    @Select("select * from github_user where id = #{id}")
    User getById(int id);

}
