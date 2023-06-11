package com.example.opt3designpatterns.Controllers;

import com.example.opt3designpatterns.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable,Observer {
    @FXML
    private TextField HurenText;
    @FXML
    private Label totaleprijs;
    @FXML
    private Button ToevoegenButton;

    @FXML
    private Label LabelList;

    @FXML
    private ListView<Product> ListSoortenProducten;

    @FXML
    private ListView<Product> OverzichtProducten;
    @FXML
    private Label CurruntLoggedInUser;

    @FXML
    private TextArea OverzichtTextarea;
    @FXML
    private TextField naamverhuur;
    @FXML
    private Label isVerhuurdLabel;
    @FXML
    private CheckBox CheckBoxVerzekering;
    @FXML
    private Button stopverhuurknop;


    @FXML
    private Button verhuurknop;

    @FXML
    void LogOut(ActionEvent event) {

    }
    public void setCurruntLoggedInUser(User user){
        CurruntLoggedInUser.setText(" Logged in for user: "+ user.getUsername());
    }
    private User user;

    public void setUser(User user){
        this.user= user;
    }
    public User getUser() {
        return user;
    }


    Product chosenprod;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListSoortenProducten.setVisible(false);
        HurenText.setVisible(false);
        ToevoegenButton.setVisible(false);
        OverzichtProducten.setVisible(false);

        ListSoortenProducten.setVisible(false);
        OverzichtProducten.setVisible(false);
        CheckBoxVerzekering.setVisible(false);
        stopverhuurknop.setVisible(false);
        verhuurknop.setVisible(false);
        naamverhuur.setVisible(false);
        totaleprijs.setVisible(false);
        isVerhuurdLabel.setVisible(false);

        inventory.registerObserver(this);
        inventory.notifyObservers();


        CheckBoxVerzekering.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    double totalPrice = chosenprod.getRentalPrice() + chosenprod.getInsuranceCost();
                    totaleprijs.setText(String.valueOf(totalPrice));
                } else {
                    double anderprijs= chosenprod.getRentalPrice();
                    totaleprijs.setText(String.valueOf(anderprijs));
                }
            }
        });

        Product car1 = new Car("Toyota", 1200, 1600);
        Product truck1 = new Truck(5000, 5000);
        Product drill1 = new Drill("Bosch", "Cordless");
        inventory.addProduct(car1);
        inventory.addProduct(truck1);
        inventory.addProduct(drill1);

        ListSoortenProducten.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item.getDescription()); // Customize this to display the desired information
                        }
                    }
                };
            }
        });

        OverzichtProducten.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new ListCell<Product>() {
                    @Override
                    protected void updateItem(Product item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item.getDescription()+ " |Is op voorraad?: |" +item.isRented()); // dit verrranderen naar vooraad nog later
//                            setText(String.valueOf(item.isRented()));
                            // Customize this to display the desired information
                        }
                    }
                };
            }
        });

        // ...


//            ListSoortenProducten.getItems().addAll(food);
            ListSoortenProducten.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>(){

                @Override
                public void changed(ObservableValue<? extends Product> observableValue, Product product, Product t1) {
                    ListSoortenProducten.setVisible(true);
                    HurenText.setVisible(true);
                    ToevoegenButton.setVisible(true);
                    OverzichtProducten.setVisible(false);

                    CheckBoxVerzekering.setVisible(false);
                    stopverhuurknop.setVisible(false);
                    verhuurknop.setVisible(false);
                    naamverhuur.setVisible(false);
                    totaleprijs.setVisible(false);
                    isVerhuurdLabel.setVisible(false);

                    chosenprod= ListSoortenProducten.getSelectionModel().getSelectedItem();
                    System.out.println(chosenprod.getDescription());
                }

            });

        OverzichtProducten.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>(){

                @Override
                public void changed(ObservableValue<? extends Product> observableValue, Product product, Product t1) {
                    ListSoortenProducten.setVisible(false);
                    HurenText.setVisible(false);
                    ToevoegenButton.setVisible(false);
                    OverzichtProducten.setVisible(true);

                    ListSoortenProducten.setVisible(false);
                    CheckBoxVerzekering.setVisible(true);
                    stopverhuurknop.setVisible(true);
                    verhuurknop.setVisible(true);
                    naamverhuur.setVisible(true);
                    totaleprijs.setVisible(true);
                    isVerhuurdLabel.setVisible(true);
                    chosenprod= OverzichtProducten.getSelectionModel().getSelectedItem();
                    String naam;
                    if (chosenprod.isRented()){
                        naam= " aan " + chosenprod.getUsername();
                    }else
                        naam=" ";
                    if (chosenprod!=null) {

                        isVerhuurdLabel.setText("item is verhuurd: " + chosenprod.isRented()+ naam);
                    }
                    if (chosenprod!= null) {

                        if (chosenprod.isOpvoorraad()) {
                            CheckBoxVerzekering.setVisible(true);
//                            if (CheckBoxVerzekering.isSelected()) {
//                                String totaleprijss;
//                                totaleprijss = String.valueOf(chosenprod.getRentalPrice() + chosenprod.getInsuranceCost());
//                                totaleprijs.setText(totaleprijss);

                                double totalPrice = chosenprod.getRentalPrice();

                                // Check if insurance checkbox is selected
                                if (CheckBoxVerzekering.isSelected()) {
                                    totalPrice += chosenprod.getInsuranceCost();
                                }

                                totaleprijs.setText(String.valueOf(totalPrice));

                            }

                        }


//                    chosenprod= OverzichtProducten.getSelectionModel().getSelectedItem();

//                    System.out.println(chosenprod.getDescription());
                }

            });
}




    @FXML
    void HandleToevoegen(ActionEvent event){
//        inventory.addProduct(chosenprod);
        chosenprod.setRented(true);

    }
@FXML
     void handleVerhuur(ActionEvent actionEvent){
        String username= naamverhuur.getText();
        chosenprod.setRented(true);
        chosenprod.setUsername(user.getUsername());

    }
    @FXML
    void handleStopverhuur(ActionEvent event){
        chosenprod.setRented(false);
        chosenprod.setUsername(null);


    }


    @FXML
    void wijsBeheer(ActionEvent event) {
        ListSoortenProducten.setVisible(true);
        HurenText.setVisible(true);
        ToevoegenButton.setVisible(true);
        OverzichtProducten.setVisible(false);

        OverzichtProducten.setVisible(false);
        CheckBoxVerzekering.setVisible(false);
        stopverhuurknop.setVisible(false);
        verhuurknop.setVisible(false);
        naamverhuur.setVisible(false);
        totaleprijs.setVisible(false);
        isVerhuurdLabel.setVisible(false);

        printObjects();


    }
    Inventory inventory = new Inventory();
//    ObservableList<Product> productList = FXCollections.observableArrayList(inventory.getProducts());

    public void showObjectInlist(){
//        ObservableList<Product> productList = FXCollections.observableArrayList(inventory.getProducts());

//        Product car1 = new Car("Toyota", 1200, 1600);
//        Product truck1 = new Truck(5000, 5000);
//        Product drill1 = new Drill("Bosch", "Cordless");
//        inventory.addProduct(car1);
//        inventory.addProduct(truck1);
//        inventory.addProduct(drill1);
//        AvailableProductDetailWindow availableProductDetailWindow = new AvailableProductDetailWindow(car1);
//        ListSoortenProducten.setItems(inventory.getProducts().get(0).getDescription());
//        ListSoortenProducten.getItems().add(car1);
//        ListSoortenProducten.getItems().add(truck1);
//        ListSoortenProducten.getItems().add(drill1);
//        ListSoortenProducten.setItems(productList);
        System.out.println(inventory.getProducts().get(0).getDescription());
        System.out.println(inventory.getProducts().get(0).getInsuranceCost());
        System.out.println(inventory.getProducts().get(0).getRentalPrice());
        System.out.println("Objects are being shown");



    }
    void printObjects(){
        ListSoortenProducten.getItems().clear();
        for(Product product: inventory.getProducts() ){
            ListSoortenProducten.getItems().add(product);
            System.out.println(product);

            product.getDescription();
//            ListSoortenProducten.getItems(product);

        }
    }
    void printoverzichtprod(){
        OverzichtProducten.getItems().clear();

        for(Product product: inventory.getProducts() ){
            OverzichtProducten.getItems().add(product);
//            System.out.println(product);
//
//            product.getDescription();
//            ListSoortenProducten.getItems(product);

        }
    }

    @FXML
    void wijsOverZicht(ActionEvent event) {
        ListSoortenProducten.setVisible(false);
        HurenText.setVisible(false);
        ToevoegenButton.setVisible(false);
        OverzichtProducten.setVisible(true);

        CheckBoxVerzekering.setVisible(false);
        stopverhuurknop.setVisible(false);
        verhuurknop.setVisible(false);
        naamverhuur.setVisible(false);
        totaleprijs.setVisible(false);
        isVerhuurdLabel.setVisible(false);
        printoverzichtprod();
        printObjects();
    }

    @Override
    public void update() {
        printoverzichtprod();

    }
}
