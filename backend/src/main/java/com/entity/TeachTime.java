package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TeachTime {
    private int No;
    private int Classes;
    private String Time;
    private int Signcode;
    private String Location;
}
