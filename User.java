package com.example.nmta.data.Account;

public class User {
    private String email, passWord,name;

    public User(String email, String passWord, String name) {
        this.email = email;
        this.passWord = passWord;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
