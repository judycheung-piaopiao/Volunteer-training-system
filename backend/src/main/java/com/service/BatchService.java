package com.service;

import com.dao.BatchDao;
import com.entity.Batch;
import com.entity.Status;
import com.util.MybatisUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BatchService {
    public static String CreateBatch(Batch batch){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try{
            BatchDao dao = sqlSession.getMapper(BatchDao.class);
            statuscode = dao.CreateBatch(batch);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    }//增加批次

    public static String DeleteBatch(int No){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try{
            BatchDao dao = sqlSession.getMapper(BatchDao.class);
            statuscode = dao.DeleteBatch(No);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    }//删除批次

    public static String GetBatch(){
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        JSONArray array = new JSONArray();
        try{
            BatchDao dao = sqlSession.getMapper(BatchDao.class);
            List<Batch> list = dao.SelectAllBatch();
            array = JSONArray.fromObject(list);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
            return array.toString();
        }
    }//获取批次

    public static String UpdateBatch(Batch batch){
        int statuscode = 0;
        SqlSession sqlSession = MybatisUtil.getSqlsession(true);
        try{
            BatchDao dao = sqlSession.getMapper(BatchDao.class);
            statuscode = dao.updateBatch(batch);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Status status = new Status(statuscode);
            JSONObject jsonObject = JSONObject.fromObject(status);
            sqlSession.close();
            return jsonObject.toString();
        }
    }//更新批次
}
