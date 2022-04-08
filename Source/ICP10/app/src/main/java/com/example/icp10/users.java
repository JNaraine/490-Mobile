package com.example.icp10;


import com.google.gson.annotations.SerializedName;

public class users {

    private int id;

    @SerializedName("login")
    private String UserName;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return UserName;
    }





}
