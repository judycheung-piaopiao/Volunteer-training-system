package com.service;

import com.dao.TeachtimeDao;
import com.entity.Status;
import com.entity.TeachTime;
import com.util.MybatisUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TeachtimeService {
    public static String addTeachtime(TeachTime teachTime){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            TeachtimeDao dao = sqlSession.getMapper(TeachtimeDao.class);
            statuscode = dao.addTeachtime(teachTime);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    } //增加教学

    public static String deleteTeachtime(int No){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            TeachtimeDao dao = sqlSession.getMapper(TeachtimeDao.class);
            statuscode = dao.deleteTeachtime(No);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    } //删除教学

    public  static String getTeachtime(int classes){
        JSONArray array = new JSONArray();
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            TeachtimeDao dao = sqlSession.getMapper(TeachtimeDao.class);
            List<TeachTime> teachtime = dao.selectTeachtime(classes);
            array = JSONArray.fromObject(teachtime);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
            return array.toString();
        }
    } //通过班级id获取教学
}
