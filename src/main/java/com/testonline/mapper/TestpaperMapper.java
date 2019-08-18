package com.testonline.mapper;

import com.testonline.bean.Testpaper;
import com.testonline.bean.TestpaperExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TestpaperMapper {
    int countByExample(TestpaperExample example);

    int deleteByExample(TestpaperExample example);

    int deleteByPrimaryKey(Integer paperid);

    int insert(Testpaper record);

    int insertSelective(Testpaper record);

    List<Testpaper> selectByExample(TestpaperExample example);

    Testpaper selectByPrimaryKey(Integer paperid);

    int updateByExampleSelective(@Param("record") Testpaper record, @Param("example") TestpaperExample example);

    int updateByExample(@Param("record") Testpaper record, @Param("example") TestpaperExample example);

    int updateByPrimaryKeySelective(Testpaper record);

    int updateByPrimaryKey(Testpaper record);
}