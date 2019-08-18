package com.testonline.controller;


import com.testonline.bean.User;
import com.testonline.controller.bean.PageBean;
import com.testonline.service.AdminService;
import com.testonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    //加载配置文件中的属性值
    @Value("${PAGE_SIZE}")
    private int PAGE_SIZE;

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    //转发
    @RequestMapping("/accept")
    public String accept() {
        //model.addAttribute("adminname",request.getParameter("adminname"));
        return "/admin_user_add";
    }

    //增加
    @RequestMapping("/adminAdd")
    public String adminAdd(HttpServletRequest request, Model model) {
        boolean flag = adminService.add(request);
        if (flag == true) {
            List<User> users = adminService.selectAll();
            model.addAttribute("users", users);
            return "redirect:/admin_user?action=add";
        } else {
            String state = "error";
            model.addAttribute("state", state);
            return "/admin_user_add";
        }
    }

    //删除某条信息
    @RequestMapping("/adminDelete")
    public String adminDelete(HttpServletRequest request, Model model) {
        adminService.delete(request);
        List<User> users = adminService.selectAll();
        model.addAttribute("users", users);
        return "redirect:/admin_user";
    }


    //修改信息
    @RequestMapping("/adminChange")
    public String adminChange(HttpServletRequest request, Model model) {

        boolean flag = adminService.update(request);
        if (flag == false) {
            String userid = request.getParameter("userid");
            String state = "error";
            return "redirect:/adminSelect?userid=" + userid + "&state=" + state;
        } else {
            List<User> users = adminService.selectAll();
            String state = "success";
            model.addAttribute("state", state);
            model.addAttribute("users", users);
            return "redirect:/admin_user";
        }
    }

    //显示用户信息
    @RequestMapping("/adminSelect")
    public String select(HttpServletRequest request, Model model) {
        User user = adminService.select(request);

        model.addAttribute("state", request.getParameter("state"));
        model.addAttribute("userid", user.getUserid());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("realname", user.getRealname());
        return "/admin_user_detail";

    }

    /*//查询所有用户信息
    @RequestMapping("/admin_user")
    public String selectALL(HttpServletRequest request, Model model) {
        List<User> users = adminService.selectAll();
        model.addAttribute("adminname", request.getParameter("adminname"));
        model.addAttribute("users", users);
        return "/admin_user";
    }*/

    //分页查询
    @RequestMapping("/admin_user")
    public String page(HttpServletRequest request,Model model){
        //当前页
        Integer pageIndex = 1;
        //每页记录条数
        int pageSize = PAGE_SIZE;
        //总记录数
        int countIndex = userService.countIndex();
        //总页数（向上取整）
        int pageCount = (int)Math.ceil((double)countIndex/pageSize);

        if(request.getParameter("pageIndex")!=null){
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        }
        //添加完跳到最后一页
        String action = request.getParameter("action");
        if("add".equals(action)){
            pageIndex = pageCount;
        }

        //index代表偏移量
        int index = (pageIndex-1)*pageSize;

        //当前页的数据
        List<User> list = userService.showlist(index,pageSize);
        PageBean<User> pageUtil = new PageBean<User>();
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setPageCount(pageCount);
        pageUtil.setPageNumber(countIndex);
        pageUtil.setPageSize(pageSize);
        pageUtil.setList(list);

        model.addAttribute("pageUtil",pageUtil);
        return "/admin_user";
    }

}
