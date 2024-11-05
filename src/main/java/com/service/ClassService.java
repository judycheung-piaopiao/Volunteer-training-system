package com.service;

import com.dao.ClassDao;
import com.entity.Class;
import com.entity.Status;
import com.util.MybatisUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClassService {
    public static String AddClass(Class classes){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            ClassDao dao = sqlSession.getMapper(ClassDao.class);
            statuscode = dao.addClass(classes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    }//增加课程

    public static String DeleteClass(int No){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            ClassDao dao = sqlSession.getMapper(ClassDao.class);
            statuscode = dao.deleteClass(No);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    }//删除课程

    public static String GetClass(int Course){
        JSONArray array = new JSONArray();
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            ClassDao dao = sqlSession.getMapper(ClassDao.class);
            List<Class> classes = dao.selectClass(Course);
            array = JSONArray.fromObject(classes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
            return array.toString();
        }
    }//通过Course的id值来获取课程信息

    public static String updateClass(Class classes){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            ClassDao dao = sqlSession.getMapper(ClassDao.class);
            statuscode = dao.updateClass(classes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    }//修改课程信息
}
