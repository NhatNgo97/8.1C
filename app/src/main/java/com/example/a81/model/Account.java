package com.example.a81.model;

public class Account {
    private String _username;
    private String _password;
    private String _fullName;

    public Account(String username, String password, String fullName) {
        _username = username;
        _password = password;
        _fullName = fullName;
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String username) {
        this._username = username;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public String getFullName() {
        return _fullName;
    }

    public void setFullName(String _fullName) {
        this._fullName = _fullName;
    }
}
