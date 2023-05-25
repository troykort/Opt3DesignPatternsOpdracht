package com.example.opt3designpatterns.Controllers;

import com.example.opt3designpatterns.Model.SessionManager;
import com.example.opt3designpatterns.Model.User;
import com.example.opt3designpatterns.Model.Userfilehandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class LoginController {
    @FXML
    private TextField Password;

    @FXML
    private Button logIn;

    @FXML
    private TextField naam;

    @FXML
    private Label statuslabel;
    User userlogin = new User();

    Userfilehandler userfilehandler= new Userfilehandler();
SessionManager sessionManager=  new SessionManager();


    @FXML
    void handleLogin(ActionEvent event) throws ParseException, IOException {
        String enteredUsername = naam.getText();
        String enteredPassword = Password.getText();
//        String enteredGmail= Gmail.getText();
        boolean inputvalid;
//        inputvalid=userfilehandler.checkifInputisvalid(enteredUsername,enteredPassword,enteredGmail);



        try {
            User loggedInUser = userlogin.login(enteredUsername, enteredPassword);

            if (loggedInUser != null) {
                System.out.println(sessionManager.getInstance().getUserSession(loggedInUser));
                if (sessionManager.getInstance().getUserSession(loggedInUser) != null) {
                    System.out.println("Error: User already has an active session!");
                    return;
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Screens/Menu.fxml"));
                Parent root = loader.load();
                MenuController controller = loader.getController();
                controller.setUser(loggedInUser);
                controller.setCurruntLoggedInUser(loggedInUser);
//                shiftManger.printShiftsForUser(loggedInUser, controller.getAListvieuwBox() );
//                controller.SettDarkMode();


                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                sessionManager.getInstance().addUserSession(loggedInUser, stage);

//                SessionManager.getInstance().getUserSession(loggedInUser);


                System.out.println("Login successful!");
            } else {
                statuslabel.setText("Verkeerde Gegevens!");
                System.out.println("login unsuccesfull!");
            }
        } catch (IOException e) {
            e.printStackTrace();
//            LoginstatusLabel.setText("Error");
        }
    }
    @FXML
    void handleSignUp(ActionEvent event) {
        String enteredUsername = naam.getText();
        String enteredPassword = Password.getText();
//        String enteredGmail= Gmail.getText();
//        if (enteredUsername.isEmpty() || enteredPassword.isEmpty() || enteredGmail.isEmpty()) {
//            LoginstatusLabel.setText("vul in alle velden");
//            return;
//        }
        try {
            List<User> users = userfilehandler.readUsersFromFile("/Storage/Userlist.txt");

            boolean usernameExists;

//            usernameExists=userlogin.UserExist(enteredUsername,enteredPassword,enteredGmail);
            usernameExists=userlogin.UserExist(enteredUsername,enteredPassword);


            if (usernameExists) {
                statuslabel.setText("User bestaat al");
                System.out.println("Somehow true");
            } else {
                // Add new user to list and write to file
                User newUser = new User(enteredUsername, enteredPassword);
                users.add(newUser);
                userfilehandler.writeUsersToFile(users, "/Storage/Userlist.txt");
//                LoginstatusLabel.setText("User created Succesfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
//            LoginstatusLabel.setText("Error");
        }
//        naam.clear();
//        Password.clear();
//        Gmail.clear();
    }
}