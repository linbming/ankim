package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping("/login.html")
    public String toLogin(HttpServletRequest request){
//    public String toLogin(){
        //判断Cookie中是否已经存在登录信息
        //①判断Cookie中用户名密码是否为null
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        System.out.println("session:username--"+username);
        System.out.println("session:password--"+password);
        if(username == null || password == null){
            //如果存在null，直接跳转到登录界面
            return "login";
        }
        //验证Cookie中的用户名密码是否正确
        if(userService.judgeUser(username,password)){
            System.out.println("abc123");
            return "redirect:/main.html";
        }else{
            return "login";
        }
    }

    @PostMapping("/myLogin")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        boolean judge = userService.judgeUser(username, password);
        System.out.println(judge);
        if(judge){
            //将登录信息保存到session域中
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            return "redirect:/main.html";
        }
        return "redirect:/login.html";
    }

    @GetMapping("/main.html")
    public String toMain(){
        return "main";
    }
}
