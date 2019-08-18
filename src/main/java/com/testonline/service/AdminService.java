package com.testonline.service;

import com.testonline.bean.Admin;
import com.testonline.bean.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AdminService {

    public boolean update(HttpServletRequest request);

    public List<User> selectAll();

    public List<Admin> login(HttpServletRequest request);

    public User select(HttpServletRequest request);

    public void delete(HttpServletRequest request);

    public boolean add(HttpServletRequest request);


}
