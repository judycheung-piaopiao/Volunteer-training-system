package com.service;

import com.dao.*;
import com.entity.Class;
import com.entity.*;
import com.util.MybatisUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    public static JSONObject importStudent(Student student,int batch){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(false);
        try {
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
            UserDao userdao = sqlSession.getMapper(UserDao.class);
            student.setBatch(batch);
            if(studentDao.checkIfExisted(student) == null)
                statuscode = studentDao.importStudent(student);
            User user = new User();
            user.setUsername(student.getEmail());
            if(userdao.SelectUser(user)==null){
                userdao.IUS(student.getEmail(),student.getNumber());
            }
            if(statuscode == 1)
                sqlSession.commit();
            else {
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
    }//导入学生

    public static String chooseClass(String name,String number,int classes,int course){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(false);
        try {
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
            TeachtimeDao teachtimeDao = sqlSession.getMapper(TeachtimeDao.class);
            ClassDao classDao = sqlSession.getMapper(ClassDao.class);
            int statuscode1 = studentDao.chooseclass(name, number, classes, course);
            int statuscode3 = classDao.addPeople(classes);
            List<TeachTime> list = teachtimeDao.selectTeachtime(classes);
            if (list==null) {//没有教学时间
                statuscode = 2;
            } else {
                for (TeachTime t : list) {
                    int no = t.getNo();
                    studentDao.addStudentTeachtime(name, number, no);
                }
                if (statuscode1 == 1&&statuscode3 == 1) {
                    statuscode = 1;
                    sqlSession.commit();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    }//选课

    public static String getStudent(String Email){
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        List<Student> students = new ArrayList<>();
        try {
            StudentDao dao = sqlSession.getMapper(StudentDao.class);
            students = dao.getStudent(Email);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JSONArray jsonArray = JSONArray.fromObject(students);
            sqlSession.close();
            return jsonArray.toString();
        }
    }//通过学生学号获取学生的信息

    public static String exitClass(String Number,int classes){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(false);
        try {
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
            TeachtimeDao teachtimeDao = sqlSession.getMapper(TeachtimeDao.class);
            ClassDao classDao = sqlSession.getMapper(ClassDao.class);
            int statuscode1 = studentDao.exitClass(Number,classes);
            int statuscode3 = classDao.deletePeople(classes);
            List<TeachTime> list = teachtimeDao.selectTeachtime(classes);
            if(list==null){
                statuscode = 2;
            }
            else{
                for(TeachTime t : list){
                    int no = t.getNo();
                    studentDao.exitTeachtime(Number,no);
                }
                if(statuscode1 == 1&&statuscode3 == 1){
                    statuscode = 1;
                    sqlSession.commit();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    }//退课

    public static String showStu(int batch){
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        List<Student> students = new ArrayList<>();
        try {
            StudentDao dao = sqlSession.getMapper(StudentDao.class);
            students = dao.showStu(batch);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JSONArray array = JSONArray.fromObject(students);
            sqlSession.close();
            return array.toString();
        }
    }//通过batch id获取该批次下的学生

    public static String deleteStduent(String Number,int batch){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try {
            StudentDao dao = sqlSession.getMapper(StudentDao.class);
            statuscode = dao.deleteStudent(Number,batch);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    }//删除该批次下的学生

    public static String showBatch(String Number){
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        List<Batch> batches = new ArrayList<>();
        try {
            BatchDao batchDao = sqlSession.getMapper(BatchDao.class);
            batches = batchDao.showBatch(Number);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JSONArray jsonArray = JSONArray.fromObject(batches);
            sqlSession.close();
            return jsonArray.toString();
        }
    }

    public static String checkSelectedClass(String Number){
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        List<Class> classes = new ArrayList<>();
        try {
            ClassDao dao = sqlSession.getMapper(ClassDao.class);
            classes = dao.findSelectedClass(Number);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JSONArray array = JSONArray.fromObject(classes);
            sqlSession.close();
            return array.toString();
        }
    }

    public static String getStudentByClass(int classId){
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        List<Student> students = new ArrayList<>();
        try {
            StudentDao dao = sqlSession.getMapper(StudentDao.class);
            students = dao.getStudentByClass(classId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JSONArray array = JSONArray.fromObject(students);
            sqlSession.close();
            return array.toString();
        }
    }
}
