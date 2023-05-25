package com.example.opt3designpatterns.Model;

import java.io.IOException;
import java.util.List;

public class User {
    private String username;
    private String password;



    public User(){

    }





    public User(String part) {

    }



    public User(String username,String password){
        this.username=username;
        this.password=password;
    }
    public void setPassword(String password) {
        this.password = password;
        System.out.println("Is the set method being called");
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



    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", Gmail=" + "]";
    }
    Userfilehandler userfilehandler= new Userfilehandler();
    public User login(String enteredUsername, String enteredPassword) throws IOException {
        List<User> users = userfilehandler.readUsersFromFile("/Storage/Userlist.txt");
        for (User user : users) {
            if (user.getUsername().equals(enteredUsername) && user.getPassword().equals(enteredPassword) ) {
                return user;
            }
        }
        return null;
    }
    public boolean UserExist(String enteredUsername,String enteredPassword) throws IOException {
        List<User> users = userfilehandler.readUsersFromFile("/Storage/Userlist.txt");
        for (User user : users) {
            if (user.getUsername().equals(enteredUsername) && user.getPassword().equals(enteredPassword) ) {
                return true;
            }
        }
        return false;
    }
    }
