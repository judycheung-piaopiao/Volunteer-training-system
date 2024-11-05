package com.dao;

import com.entity.Class;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassDao {
    int addClass(Class classes);
    int deleteClass(@Param("No")int No);
    List<Class> selectClass(@Param("Course") int Course);
    int addPeople(@Param("No")int No);
    int deletePeople(@Param("No")int No);
    List<Class> findSelectedClass(@Param("Number")String Number);
    List<Class> findClassByTeacherNo(@Param("TeacherNo")String Number);
    int updateClass(Class classes);
}
