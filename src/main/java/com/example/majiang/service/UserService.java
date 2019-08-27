package com.example.majiang.service;

import com.example.majiang.dto.GithubUser;
import com.example.majiang.mapper.UserMapper;
import com.example.majiang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void UserAddOrUpdate(GithubUser user){
        User u = userMapper.getById(user.getId());

        if(u == null){
            // 插入数据库
            User user1 = new User();
            user1.setAvatar_url(user.getAvatar_url());
            user1.setId(user.getId());
            user1.setLogin(user.getLogin());
            user1.setName(user.getName());
            userMapper.insert(user1);
        }else{
            u.setName(user.getName());
            u.setAvatar_url(user.getAvatar_url());
            userMapper.update(u);
            System.out.println("已经有用户信息！");
        }
    }
}
