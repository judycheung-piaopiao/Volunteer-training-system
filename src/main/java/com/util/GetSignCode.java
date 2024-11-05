package com.util;

import java.util.Random;

public class GetSignCode {
    public static int getSignCode(){
        Random random = new Random();
        return random.nextInt(10000)%(10000-1000+1) + 1000;
    }
}
