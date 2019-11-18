package com.example.adminmyclub.Beans;

public class UserPassBeans {
    String uname;
    String pass;

    public UserPassBeans() {

    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UserPassBeans(String uname, String pass) {
        this.uname = uname;
        this.pass = pass;
    }
}
