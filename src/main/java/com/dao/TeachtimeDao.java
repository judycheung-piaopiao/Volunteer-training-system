package com.dao;

import com.entity.TeachTime;

import java.util.List;

public interface TeachtimeDao {
    int addTeachtime(TeachTime teachTime);
    int deleteTeachtime(int No);
    List<TeachTime> selectTeachtime(int Classes);
}
