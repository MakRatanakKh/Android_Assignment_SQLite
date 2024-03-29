package com.example.mak_ratanak_assignment_sqlite.model;

public class UserModel {
    private String email, password;

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserModel(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
