package com.daimao.controller;


import com.daimao.exception.AppException;
import com.daimao.model.User;
import com.daimao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @PostMapping("/register")
    public Object register(User user, MultipartFile headFile) {
        //校验请求数据

        //保存上传的用户头像到服务端本地
        if(headFile != null) {
            String head = userService.saveHead(headFile);
            //上传的路径映射为http服务路径
            //用户头像的http路径设置到user.head，把user插入数据库
            user.setHead(head);
        }
        userService.register(user);
        return null;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Object login(@RequestBody User user, HttpServletRequest request) {
        User exist = userService.queryByUsername(user.getUsername());
        if(exist == null) {
            throw new AppException("LOG001","用户不存在");
        }
        if(!exist.getPassword().equals(user.getPassword())) {
            throw new AppException("LOG002","用户名或密码错误");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",exist);
        return null;
    }

    /**
     * 注销
     */
    @GetMapping("/logout")
    public Object logout(HttpSession session) {
        session.removeAttribute("user");
        return null;
    }
}
