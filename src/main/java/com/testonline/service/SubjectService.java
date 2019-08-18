package com.testonline.service;

import com.testonline.bean.Subject;
import com.testonline.bean.SubjectExample;

import java.util.List;

public interface SubjectService {

    Subject selectSubjectById(int subjectId);

    List<Subject> listSubject();

    public int add(Subject subject);

    public int delete(Integer testid);

    public int update(Subject subject);

    public List<Subject> list(SubjectExample subjectExample);
}
