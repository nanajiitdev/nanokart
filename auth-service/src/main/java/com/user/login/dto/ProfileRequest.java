package com.user.login.dto;

public class ProfileRequest {

    private String name;

    private String mobile;

    public ProfileRequest() {
    }

    public ProfileRequest(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}