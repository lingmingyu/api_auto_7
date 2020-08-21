package com.lemon.auto.V3;

public class RegisterParam  {
    private  String userName;
    private  String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public RegisterParam(String userName,String password){
        super();
        this.userName=userName;
        this.password=password;
    }
    @Override
    public String toString() {
        return "userName="+userName+",password="+password;
    }
}
