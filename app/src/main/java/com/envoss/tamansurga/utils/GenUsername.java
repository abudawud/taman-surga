package com.envoss.tamansurga.utils;

public class GenUsername {
    public static String genUsername(String str){
        int len = str.length();
        return str.substring(len - 5, len);
    }
}
