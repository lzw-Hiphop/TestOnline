package com.testonline.service.Impl;

import com.testonline.bean.Subject;
import com.testonline.bean.SubjectExample;
import com.testonline.mapper.SubjectMapper;
import com.testonline.service.SubjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Resource
    SubjectMapper subjectMapper;

    @Override
    public Subject selectSubjectById(int subjectId) {
        return subjectMapper.selectByPrimaryKey(subjectId);
    }

    @Override
    public List<Subject> listSubject() {
        SubjectExample subjectExample = new SubjectExample();
        return subjectMapper.selectByExample(subjectExample);
    }

    @Override
    public int add(Subject subject) {
        return subjectMapper.insertSelective(subject);
    }

    @Override
    public int delete(Integer testid) {
        return subjectMapper.deleteByPrimaryKey(testid);
    }

    @Override
    public int update(Subject subject) {
        return subjectMapper.updateByPrimaryKeySelective(subject);
    }

    @Override
    public List<Subject> list(SubjectExample subjectExample) {
        return subjectMapper.selectByExample(subjectExample);
    }
}
