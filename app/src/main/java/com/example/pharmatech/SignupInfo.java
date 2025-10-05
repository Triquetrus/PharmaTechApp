package com.example.pharmatech;

public class SignupInfo {

    public SignupInfo() {
    }

    String username;
    String email;
    String password;
    String number;
    String id;
    String role;

    public SignupInfo(String username, String email, String password, String number, String id, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.number = number;
        this.id = id;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
