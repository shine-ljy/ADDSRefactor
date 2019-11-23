package com.java.adds.service;


import com.java.adds.dao.UserDao;
import com.java.adds.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    /**ljy
     * 用户登录
     *
     */
    public UserEntity login(String login_name)
    {
        return userDao.login(login_name);
    }


    /**ljy
     * 用户注册
     * @return
     */
    public boolean userRegister(UserEntity userEntity)
    {
        return userDao.userRegister(userEntity);
    }


    /**ljy
     *管理员获取所有用户信息
     * @rturn
     */
    public ArrayList<UserEntity> getAllUsers()
    {
        return userDao.getAllUsers();
    }
}
