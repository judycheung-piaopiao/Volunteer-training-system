package com.dao;

import com.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    int importStudent(Student student);
    int chooseclass (@Param("Name")String Name, @Param("Number")String Number,@Param("Class")int classes,@Param("Course") int Course);
    int addStudentTeachtime(@Param("Name")String Name,@Param("Number")String Number,@Param("Teachtime")int Teachtime);
    int exitClass(@Param("Number")String Number,@Param("Class")int Classes);
    int exitTeachtime(@Param("Number")String Number,@Param("Teachtime")int Teachtime);
    List<Student> getStudent(@Param("Email")String Email);
    Student checkIfExisted(Student student);
    List<Student> showStu(@Param("Batch")int batch);
    int deleteStudent(@Param("Number")String Number,@Param("Batch")int batch);
    List<Student> getStudentByClass(@Param("Classes")int Classes);
}
