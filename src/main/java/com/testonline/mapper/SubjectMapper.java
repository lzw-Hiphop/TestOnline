package com.testonline.mapper;

import com.testonline.bean.SubjectExample;
import com.testonline.bean.Subject;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SubjectMapper {
    int countByExample(SubjectExample example);

    int deleteByExample(SubjectExample example);

    int deleteByPrimaryKey(Integer subjectid);

    int insert(Subject record);

    int insertSelective(Subject record);

    List<Subject> selectByExample(SubjectExample example);

    Subject selectByPrimaryKey(Integer subjectid);

    int updateByExampleSelective(@Param("record") Subject record, @Param("example") SubjectExample example);

    int updateByExample(@Param("record") Subject record, @Param("example") SubjectExample example);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);
}