package com.testonline.service.Impl;

import com.testonline.bean.User;
import com.testonline.bean.UserExample;
import com.testonline.mapper.UserMapper;
import com.testonline.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper usermapper;


    //修改
    @Override
    public boolean change(HttpServletRequest request) {
        boolean flag = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String realname = request.getParameter("realname");
        String newpassword = request.getParameter("newpassword");

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);

        List<User> users = usermapper.selectByExample(example);
        User user = users.get(0);
        if (password == "") {
            user.setRealname(realname);
            usermapper.updateByExampleSelective(user, example);
            flag = true;

        } else if (password.equals(user.getPassword())) {
            user.setPassword(newpassword);
            user.setRealname(realname);
            usermapper.updateByExampleSelective(user, example);
            flag = true;
        }
        return flag;
    }

    //登陆
    @Override
    public List<User> login(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> users = usermapper.selectByExample(example);
        return users;

    }

    //注册
    @Override
    public User register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String realname = request.getParameter("realname");

        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = usermapper.selectByExample(example);

        if (users.size() != 0) {
            return null;
        } else {
            User newuser = new User();
            newuser.setUsername(username);
            newuser.setPassword(password);
            newuser.setRealname(realname);
            usermapper.insertSelective(newuser);
            return newuser;
        }
    }

    //用户信息
    @Override
    public List<User> select(HttpServletRequest request) {
        String username = request.getParameter("username");
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = usermapper.selectByExample(example);
        return users;
    }


    //删除某个用户
    @Override
    public void delete(HttpServletRequest request) {
        String username = request.getParameter("username");
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        usermapper.deleteByExample(example);
    }

    @Override
    public User selectByUserId(int userId) {
        return usermapper.selectByPrimaryKey(userId);
    }

    //查询用户列表
    @Override
    public List<User> showlist(int index,int pageSize) {
        return usermapper.showlist(index,pageSize);
    }

    //查询用户数据的总条数
    @Override
    public int countIndex() {
        return usermapper.countIndex();
    }

    //上传头像
    @Override
    public void upload(String username,String newFileName){
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        User user = new User();
        user.setImage(newFileName);
        usermapper.updateByExampleSelective(user,example);
    }
}
