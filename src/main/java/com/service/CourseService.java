package com.service;

import com.dao.CourseDao;
import com.entity.Course;
import com.entity.Status;
import com.util.MybatisUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CourseService {
    public static String AddCourse(Course course){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            CourseDao dao = sqlSession.getMapper(CourseDao.class);
            statuscode = dao.addCourse(course);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    }//增加课程

    public static String DeleteCourse(int No){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            CourseDao dao = sqlSession.getMapper(CourseDao.class);
            statuscode = dao.deleteCourse(No);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    }//删除课程

    public static String GetCourse(int Batch){
        JSONArray array = new JSONArray();
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            CourseDao dao = sqlSession.getMapper(CourseDao.class);
            List<Course> courses = dao.selectAllCourse(Batch);
            array = JSONArray.fromObject(courses);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
            return array.toString();
        }
    }//通过batch id获取课程

    public static String UpdateCourse(Course course){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            CourseDao dao = sqlSession.getMapper(CourseDao.class);
            statuscode = dao.updateCourse(course);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    }
}
