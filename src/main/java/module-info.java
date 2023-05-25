module com.example.opt3designpatterns {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.opt3designpatterns to javafx.fxml;
    exports com.example.opt3designpatterns;
    exports com.example.opt3designpatterns.Controllers;
    opens com.example.opt3designpatterns.Controllers to javafx.fxml;
}