package com.example.task;

public class Users  {
    String name;
    String email;
    String pass;
    String uid;

    public Users() {
    }



    public Users(String name, String email, String uid,String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.uid = uid;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
