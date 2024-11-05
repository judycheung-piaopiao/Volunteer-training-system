package com.dao;

import com.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao {
    int addCourse(Course course);
    int deleteCourse(@Param("No") int No);
    List<Course> selectAllCourse(@Param("Batch") int Batch);
    int updateCourse(Course course);
}
