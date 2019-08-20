package com.testonline.controller;

import com.testonline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    private UserService userService;

    @RequestMapping("/saveImg")
    public String saveImg(@RequestParam("pic")MultipartFile file, HttpServletRequest request, Model model) throws Exception {
        // 保存图片的路径，图片上传成功后，将路径保存到数据库
        String filePath = request.getSession().getServletContext().getRealPath("img");
        // 生成文件新的名字
        String newFileName = UUID.randomUUID() + ".jpg";
        // 封装上传文件位置的全路径
        File targetFile = new File(filePath, newFileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        file.transferTo(targetFile);
        //获得此用户名
        String username = request.getParameter("username");
        //将文件路径存进数据库
        userService.upload(username,newFileName);
        model.addAttribute("newFileName",newFileName);
        return "/user_info";
    }

    @RequestMapping("/uploadpage")
    public String uploadpage(String username,Model model){
        model.addAttribute("username",username);
        return "/uploadpage";
    }

}
