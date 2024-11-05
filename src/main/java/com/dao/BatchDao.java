package com.dao;

import com.entity.Batch;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BatchDao {
    int CreateBatch(Batch batch);
    int DeleteBatch(@Param("No") int No);
    List<Batch> SelectAllBatch();
    List<Batch> showBatch(String Number);
    int updateBatch(Batch batch);
}
