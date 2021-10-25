package com.example.tabtemplets.DataVeriables;

public class UserData {

    public String name, email, password, phone;
    public UserData() {
    }

    public UserData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserData(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
