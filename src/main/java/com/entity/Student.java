package com.entity;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    private int No;
    private String Name;
    private String Number;
    private String Sex;
    private String Email;
    private String Academy;
    private int Batch;
    private int Classes;
    private int arrived;
    private String Other;
}
