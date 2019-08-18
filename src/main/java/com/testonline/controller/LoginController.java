package com.testonline.controller;

import com.testonline.bean.Admin;
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
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    //转发
    @RequestMapping("/tologin")
    public String tologin(){
        return "/login";
    }

    //登陆
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model) {

        //获得用户级别
        String userLevel = request.getParameter("userLevel");
        String str = null;

        //学生登陆
        if (userLevel.equals("student")) {
            List<User> users = userService.login(request);
            if (users.size() == 0) {
                str = "redirect:/tologin?state=error";
            } else {
                //model.addAttribute("username", users.get(0).getUsername());

                //保存登陆状态到session
                request.getSession().setAttribute("userId", users.get(0).getUserid());
                request.getSession().setAttribute("username", users.get(0).getUsername());
                request.getSession().setAttribute("realName", users.get(0).getRealname());
                request.getSession().setAttribute("userLevel", "student");
                str = "redirect:/test_info";
            }
        } else if (userLevel.equals("admin")) {
            //管理员登陆
            List<Admin> admins = adminService.login(request);
            if (admins.size() == 0) {
                str = "redirect:/tologin?state=error";
            } else {
                List<User> users = adminService.selectAll();
                model.addAttribute("adminname", admins.get(0).getAdminname());
                model.addAttribute("users", users);
                str = "redirect:/admin_test";

                //保存登陆状态到session
                request.getSession().setAttribute("userId", admins.get(0).getAdminid());
                request.getSession().setAttribute("username", admins.get(0).getAdminname());
                request.getSession().setAttribute("realName", admins.get(0).getRealname());
                request.getSession().setAttribute("userLevel", "admin");
            }
        }
        return str;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/tologin";
    }


}
