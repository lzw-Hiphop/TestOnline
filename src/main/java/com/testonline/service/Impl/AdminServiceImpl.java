package com.testonline.service.Impl;

import com.testonline.bean.Admin;
import com.testonline.bean.AdminExample;
import com.testonline.bean.User;
import com.testonline.bean.UserExample;
import com.testonline.mapper.AdminMapper;
import com.testonline.mapper.UserMapper;
import com.testonline.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminmapper;
    @Resource
    private UserMapper usermapper;


    //增加
    @Override
    public boolean add(HttpServletRequest request) {
        boolean flag = false;

        String username = request.getParameter("username");
        String realname = request.getParameter("realname");
        String password = request.getParameter("newPassword");
        //检查用户名是否存在
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = usermapper.selectByExample(example);

        if (users.size() == 0) {
            User user = new User();
            user.setUsername(username);
            user.setRealname(realname);
            user.setPassword(password);
            usermapper.insert(user);
            flag = true;
        }
        return flag;
    }

    //删除
    @Override
    public void delete(HttpServletRequest request) {
        Integer userid = Integer.parseInt(request.getParameter("userid"));
        usermapper.deleteByPrimaryKey(userid);
    }

    //修改
    @Override
    public boolean update(HttpServletRequest request) {
        boolean flag = true;
        Integer userid = Integer.parseInt(request.getParameter("userid"));
        UserExample example = new UserExample();
        example.createCriteria().andUseridEqualTo(userid);

        String username = request.getParameter("username");
        String realname = request.getParameter("realname");
        String newPassword = request.getParameter("newPassword");
        //检查用户名是否已存在
        UserExample example2 = new UserExample();
        example2.createCriteria().andUsernameEqualTo(username);
        List<User> users = usermapper.selectByExample(example2);

        User user = new User();
        //数据库是否有除自己外的username相同的用户
        if (users.size() != 0 && userid != users.get(0).getUserid()) {
            flag = false;
            return flag;
        }

        if (newPassword == "") {
            user.setUsername(username);
            user.setRealname(realname);
        } else {
            user.setUsername(username);
            user.setRealname(realname);
            user.setPassword(newPassword);
        }
        usermapper.updateByExampleSelective(user, example);
        return flag;
    }

    //登陆
    @Override
    public List<Admin> login(HttpServletRequest request) {
        String adminname = request.getParameter("username");
        String password = request.getParameter("password");
        AdminExample example = new AdminExample();
        example.createCriteria().andAdminnameEqualTo(adminname).andPasswordEqualTo(password);
        List<Admin> admins = adminmapper.selectByExample(example);
        return admins;

    }

    //用户信息后台管理（返回所有学生用户）
    @Override
    public List<User> selectAll() {
        UserExample example = new UserExample();
        example.createCriteria().andUseridIsNotNull();
        List<User> users = usermapper.selectByExample(example);
        return users;
    }

    @Override
    public User select(HttpServletRequest request) {
        Integer userid = Integer.parseInt(request.getParameter("userid"));
        User user = usermapper.selectByPrimaryKey(userid);
        return user;
    }


}
