package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Teacher {
    private int No;
    private String Name;
    private String Number;
    private int Classes;
}
