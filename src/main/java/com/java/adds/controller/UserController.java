package com.java.adds.controller;


import com.java.adds.controller.vo.LoginVO;
import com.java.adds.entity.QuestionEntity;
import com.java.adds.entity.UserEntity;
import com.java.adds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public UserEntity login(@RequestBody LoginVO loginVO, HttpServletResponse httpServletResponse)
    {//HttpServletResponse httpServletResponse
        UserEntity userEntity = userService.login(loginVO.getLogin_name());
        if(userEntity==null)
            httpServletResponse.setStatus(401);  //用户不存在
        else if(!userEntity.getPassword().equals(loginVO.getPassword()))
            httpServletResponse.setStatus(402);  //密码错误
        else
            httpServletResponse.setStatus(200);  //登录成功
        return userEntity;
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


    /**ljy
     *QA检索
     * @return
     */
    @PostMapping("simpleQuestion")
    public ArrayList<QuestionEntity> searchSimpleQuestion(@RequestBody String question)
    {
        return userService.searchSimpleQuestion(question);
    }
}
