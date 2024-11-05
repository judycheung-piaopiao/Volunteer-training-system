package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Class {
    private int No;
    private String Name;
    private String Location;
    private String TeacherNo;
    private String TeacherName;
    private int MaxPeople;
    private int Course;
    private int Nowpeople;
    private String Time;
}
