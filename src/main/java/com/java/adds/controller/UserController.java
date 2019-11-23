package com.java.adds.controller;


import com.java.adds.controller.vo.LoginVO;
import com.java.adds.entity.UserEntity;
import com.java.adds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    /**ljy
     *用户登录
     * @return
     */
    @PostMapping("login")
    public Integer login(@RequestBody LoginVO loginVO)
    {
        UserEntity userEntity = userService.login(loginVO.getLogin_name());
        if(userEntity==null)
            return 401;  //用户不存在
        else if(!userEntity.getPassword().equals(loginVO.getPassword()))
            return 402;  //密码错误
        else
            return userEntity.getType(); //返回登录角色
    }

    /**ljy
     * 用户注册
     * @return
     */
    @PutMapping("register")
    public boolean userRegister(@RequestBody UserEntity userEntity)
    {
        return userService.userRegister(userEntity);
    }

    /**ljy
     *管理员获取所有用户信息
     * @rturn
     */
    @GetMapping("")
    public ArrayList<UserEntity> getAllUsers()
    {
        return userService.getAllUsers();
    }
}