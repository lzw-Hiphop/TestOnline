package com.testonline.service.Impl;

import com.testonline.bean.Test;
import com.testonline.bean.TestExample;
import com.testonline.bean.Testpaper;
import com.testonline.bean.TestpaperExample;
import com.testonline.mapper.SubjectMapper;
import com.testonline.mapper.TestMapper;
import com.testonline.mapper.TestpaperMapper;
import com.testonline.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    TestMapper testMapper;
    @Resource
    TestpaperMapper testpaperMapper;
    @Resource
    SubjectMapper subjectMapper;


    @Override
    public List<Test> list(TestExample testExample) {
        return testMapper.selectByExample(testExample);
    }

    @Override
    public Test get(Integer testid) {
        return testMapper.selectByPrimaryKey(testid);
    }


    @Override
    public int add(Test test) {
        /*
        TestExample testExample = new TestExample();
        testExample.setOrderByClause("testid DESC");
        int testId;
        if (testMapper.selectByExample(testExample).size() == 0) {
            testId = 1;
        } else {
            testId = testMapper.selectByExample(testExample).get(0).getTestid() + 1;
        }
        test.setTestid(testId);
        */
        return testMapper.insert(test);
    }

    @Override
    public int delete(Integer testid) {
        return testMapper.deleteByPrimaryKey(testid);
    }

    @Override
    public int update(Test test) {
        return testMapper.updateByPrimaryKeySelective(test);
    }

    @Override
    public List<Testpaper> listUncompletedPaper(int userId) {
        TestExample testExample = new TestExample();
        testExample.createCriteria().andUseridEqualTo(userId);
        List<Test> tests = testMapper.selectByExample(testExample);
        List<Integer> completedPaperIds = new ArrayList<>();
        for (Test test : tests) {
            completedPaperIds.add(test.getPaperid());
        }
        if (completedPaperIds.size() == 0) {
            completedPaperIds.add(0);
        }
        TestpaperExample testpaperExample = new TestpaperExample();
        TestpaperExample.Criteria criteria = testpaperExample.createCriteria();
        criteria.andPaperidNotIn(completedPaperIds);
        criteria.andPublishedEqualTo(1);
        List<Testpaper> testpapers = testpaperMapper.selectByExample(testpaperExample);

        return testpapers;
    }

    @Override
    public void delebeByPaperId(int paperId) {
        TestExample testExample = new TestExample();
        testExample.createCriteria().andPaperidEqualTo(paperId);
        testMapper.deleteByExample(testExample);
    }

    @Override
    public Test selectByPaperIdAndUserId(int paperId, int userId) {
        TestExample testExample = new TestExample();
        TestExample.Criteria criteria = testExample.createCriteria();
        criteria.andPaperidEqualTo(paperId);
        criteria.andUseridEqualTo(userId);
        List<Test> tests = testMapper.selectByExample(testExample);
        if (tests.size() != 0) {
            return tests.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Test> selectByUserId(int userId) {
        TestExample testExample = new TestExample();
        testExample.createCriteria().andUseridEqualTo(userId);
        return testMapper.selectByExample(testExample);
    }

    @Override
    public List<Test> selectByPaperId(int paperId) {
        TestExample testExample = new TestExample();
        testExample.createCriteria().andPaperidEqualTo(paperId);
        return testMapper.selectByExample(testExample);
    }

}
