package com.example.opt3designpatterns.Model;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Userfilehandler {
    public void writeUsersToFile(List<User> users, String fileName) throws IOException {
        URI uri = null;
        try {
            uri = getClass().getResource(fileName).toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        File file = new File(uri);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (User user : users) {
            writer.write(user.getUsername() + "," + user.getPassword() +","+"\n"); // Assuming username and password are comma-separated
        }
        writer.close();
    }
    public List<User> readUsersFromFile(String fileName) throws IOException {
        List<User> users = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            String [] parts = line.split(",");
            User user = new User(parts[0], parts[1]); // Assuming username and password are comma-separated
            users.add(user);
        }
        reader.close();
        return users;
    }
}
