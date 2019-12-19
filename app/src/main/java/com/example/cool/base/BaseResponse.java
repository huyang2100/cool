package com.example.cool.base;

import java.util.ArrayList;

public class BaseResponse<T> {
    public int code;
    public String message;
    public ArrayList<T> data;
}
