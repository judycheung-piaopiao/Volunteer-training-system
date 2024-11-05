package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    private int No;
    private String Name;
    private String Description;
    private int Batch;
    private int Mustchose;
}
