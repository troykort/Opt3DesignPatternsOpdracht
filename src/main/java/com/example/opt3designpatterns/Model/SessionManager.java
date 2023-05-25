package com.example.opt3designpatterns.Model;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static SessionManager instance;
    private Map<User, Stage> userSessions;

    public SessionManager() {
        userSessions = new HashMap<>();
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void addUserSession(User user, Stage stage) {
        userSessions.put(user, stage);
    }

    public Stage getUserSession(User user) {
        return userSessions.get(user);
    }

    public void removeUserSession(User user) {
        userSessions.remove(user);
    }
}
