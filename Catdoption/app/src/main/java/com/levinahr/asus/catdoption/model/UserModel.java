package com.levinahr.asus.catdoption.model;

public class UserModel {
    private String username;
    private String password;
    private int success;
    private String message;

    public UserModel(String username, String email, String password, int success, String message) {
        this.username = username;
        this.password = password;
        this.success = success;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
