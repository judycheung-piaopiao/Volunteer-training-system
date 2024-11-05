package com.service;

import com.dao.UserDao;
import com.entity.Status;
import com.entity.User;
import com.util.MybatisUtil;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;

public class UserService {
    public static String Userlogin(User user){
        int statuscode = 0;//输入错误
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try{
            UserDao userdao = sqlSession.getMapper(UserDao.class);
            User usercheck = userdao.SelectUser(user);
            if(usercheck == null) statuscode = 2;//没有该用户
            else{
                if((usercheck.getUserlevel()).equals(user.getUserlevel()) && (usercheck.getPassword()).equals(user.getPassword())){
                    statuscode = 3;//密码错误
                }
                else statuscode = 1;//登录成功
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    } //修改密码
    public static String Changpassword(String username,String password){
        int statuscode = 0;//输入错误
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try{
            UserDao userdao = sqlSession.getMapper(UserDao.class);
            statuscode = userdao.changePassword(username,password);
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
