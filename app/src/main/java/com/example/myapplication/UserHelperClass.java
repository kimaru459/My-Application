package com.example.myapplication;

public class UserHelperClass {
    String username, email, phonenumber, password;


    public UserHelperClass(String username, String email, String phonenumber, String password) {
        this.username = username;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
    }

    public String getUsername() {


        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getEmail() {


        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPhonenumber() {

        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {

        this.phonenumber = phonenumber;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
}
