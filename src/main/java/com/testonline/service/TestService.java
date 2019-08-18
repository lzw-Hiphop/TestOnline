package com.testonline.service;

import com.testonline.bean.Test;
import com.testonline.bean.TestExample;
import com.testonline.bean.Testpaper;

import java.util.List;

public interface TestService {
    public List<Test> list(TestExample testExample);

    public Test get(Integer testid);

    public int add(Test test);

    public int delete(Integer testid);

    public int update(Test test);

    List<Test> selectByUserId(int userId);

    List<Test> selectByPaperId(int paperId);

    List<Testpaper> listUncompletedPaper(int UserId);

    void delebeByPaperId(int paperId);

    Test selectByPaperIdAndUserId(int paperId, int userId);


}
