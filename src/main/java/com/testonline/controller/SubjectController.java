package com.testonline.controller;

import com.testonline.bean.Subject;
import com.testonline.bean.SubjectExample;
import com.testonline.mapper.SubjectMapper;
import com.testonline.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SubjectController {

    @Resource
    private SubjectService subjectService;
    @Resource
    private SubjectMapper subjectMapper;

    @RequestMapping("/admin_subject_add/execute")
    public String adminSubjectAddExecute(String subjectname) {
        Subject subject = new Subject();
        subject.setSubjectname(subjectname);
        subjectService.add(subject);

        return "redirect:/admin_subject";
    }

    @RequestMapping("/admin_subject_add")
    public String adminSubjectAdd() {
        return "/admin_subject_add";
    }


    @RequestMapping("/admin_subject/delete")
    public String adminSubjectDelete(Integer subjectid) {
        subjectService.delete(subjectid);
        return "redirect:/admin_subject";
    }

    @RequestMapping("/admin_subject_detail/update")
    public String adminSubjectDetailUpdate(Integer subjectid, String subjectname) {
        System.out.println(subjectid);
        System.out.println(subjectname);
        Subject subject = subjectMapper.selectByPrimaryKey(subjectid);
        subject.setSubjectname(subjectname);
        subjectService.update(subject);
        System.out.println(subject);
        return "redirect:/admin_subject";
    }

    @RequestMapping("/admin_subject")
    public String adminSubject(Model model) {
        SubjectExample subjectExample = new SubjectExample();
        List<Subject> subjects = subjectService.list(subjectExample);
        model.addAttribute("subjects", subjects);
        return "/admin_subject";
    }

    @RequestMapping("/admin_subject_detail")
    public String adminSubjectDetail(Model model, Integer subjectid, String subjectname) {
        try {
            String newsubjectname = new String(subjectname.getBytes("iso-8859-1"), "utf-8");
            //System.out.println(newsubjectname);
            model.addAttribute("subjectidtemp", subjectid);
            model.addAttribute("subjectnametemp", newsubjectname);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/admin_subject_detail";
    }

}
