package com.testonline.service;

import com.testonline.bean.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    public boolean change(HttpServletRequest request);

    public List<User> login(HttpServletRequest request);

    public User register(HttpServletRequest request);

    public List<User> select(HttpServletRequest request);

    public void delete(HttpServletRequest request);

    User selectByUserId(int userId);

    public List<User> showlist(int index,int pageSize);

    public int countIndex();

    public void upload(String username,String newFileName);
}
