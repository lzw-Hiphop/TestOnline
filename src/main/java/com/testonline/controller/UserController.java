package com.testonline.controller;

import com.testonline.bean.User;
import com.testonline.service.AdminService;
import com.testonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    //转发
    @RequestMapping("/toregister")
    public String toregister(HttpServletRequest request){
        return "/register";
    }

    //用户注册
    @RequestMapping("/register")
    public String register(HttpServletRequest request, Model model) {
        User user = userService.register(request);
        if (user == null) {
            return "redirect:/toregister?state=error";
        } else {
            model.addAttribute("username", user.getUsername());
            return "redirect:/toregister?state=success";
        }
    }


    //修改信息
    @RequestMapping("/userChange")
    public String changeInfo(HttpServletRequest request, Model model) {
        boolean flag = userService.change(request);
        String password = request.getParameter("password");
        if (flag == true && password != "") {
            String username = request.getParameter("username");
            request.getSession().invalidate();//销毁session
            return "redirect:/user_info?username=" + username + "&password=true";
        } else if (flag == true && password == "") {
            model.addAttribute("username", request.getParameter("username"));
            request.getSession().setAttribute("realName", request.getParameter("realname"));
            return "redirect:/user_info?username=" + request.getParameter("username") + "&state=success";
        } else {
            return "redirect:/user_info?username=" + request.getParameter("username") + "&password=false";
        }
    }

    //用户信息管理
    @RequestMapping("/user_info")
    public String userInfo(Model model, HttpServletRequest request) {
        List<User> users = userService.select(request);
        User user = users.get(0);
        model.addAttribute("newFileName",user.getImage());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("realname", user.getRealname());
        return "/user_info";
    }

}
