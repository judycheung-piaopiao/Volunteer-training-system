package com.service;

import com.dao.ClassDao;
import com.dao.TeacherDao;
import com.dao.UserDao;
import com.entity.Class;
import com.entity.Status;
import com.entity.Teacher;
import com.entity.User;
import com.util.MybatisUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TeacherService {
    public static JSONObject importTeacher(Teacher teacher){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(false);
        try {
            TeacherDao teacherDao = sqlSession.getMapper(TeacherDao.class);
            UserDao userdao = sqlSession.getMapper(UserDao.class);
            int statuscode1 = teacherDao.insertTeacher(teacher);
            String number = teacher.getNumber();
            User user = new User();
            user.setUsername(number);
            if(userdao.SelectUser(user)==null) userdao.IUT(number);
            sqlSession.commit();
            if(statuscode1 == 1){
                statuscode = 1;
            }
            else{
                sqlSession.rollback();
            }
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            Status status = new Status(statuscode);
            JSONObject object = JSONObject.fromObject(status);
            sqlSession.close();
            return object;
        }
    } //导入教师

    public static String selectClassByTeacherNo(String TeacherNo){
        JSONArray array = new JSONArray();
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            ClassDao dao = sqlSession.getMapper(ClassDao.class);
            List<Class> list = dao.findClassByTeacherNo(TeacherNo);
            array = JSONArray.fromObject(list);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
            return array.toString();
        }
    }
}
