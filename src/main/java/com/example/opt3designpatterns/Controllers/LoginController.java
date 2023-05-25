package com.example.opt3designpatterns.Controllers;

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
    User userlogin = new User();

    Userfilehandler userfilehandler= new Userfilehandler();

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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Screens/Menu.fxml"));
                Parent root = loader.load();
                MenuController controller = loader.getController();
                controller.setUser(loggedInUser);
                controller.setCurruntLoggedInUser(loggedInUser);
//                shiftManger.printShiftsForUser(loggedInUser, controller.getAListvieuwBox() );
//                controller.SettDarkMode();


                Stage stage = (Stage) logIn.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
//                userfilehandler.writelastUserTofile(loggedInUser,"/resources/TextFiles/LastLoggedInUser.txt");

                System.out.println("Login successful!");
            } else {
//                LoginstatusLabel.setText("Verkeerde Gegevens!");
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
usernameExists=false;


            if (usernameExists) {
//                LoginstatusLabel.setText("User bestaat al");
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