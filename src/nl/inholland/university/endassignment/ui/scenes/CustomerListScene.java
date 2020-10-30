package nl.inholland.university.endassignment.ui.scenes;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.inholland.university.endassignment.data.DataSeed;
import nl.inholland.university.endassignment.data.UserDao;
import nl.inholland.university.endassignment.models.Article;
import nl.inholland.university.endassignment.models.Customer;
import nl.inholland.university.endassignment.ui.StyledScene;

import java.util.ArrayList;
import java.util.List;

public class CustomerListScene {
    private Stage stage;
    public Stage getStage() {
        return stage;
    }
    UserDao userDao = new UserDao(DataSeed.getDbInstance());
    private  CreateOrderScene orderScene;
    public CustomerListScene(CreateOrderScene createOrderScene){
        stage = new Stage();
        this.orderScene = createOrderScene;
        VBox layout = new VBox();
        layout.setPadding(new Insets(30));
        layout.setSpacing(20);
        Label customerList = new Label("Customer List");
        customerList.setTextFill(Color.WHITE);
        customerList.setFont(new Font("Arial",20));

        //Creating table View
        TableView<Customer> customerTableView  = new TableView<>();
        getTableView(customerTableView);
        customerTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                List<Customer>customers;
                customers = customerTableView.getSelectionModel().getSelectedItems();
                Customer selectedCustomer = (Customer)customers.get(0);
                orderScene.customerInfo(selectedCustomer);
                stage.close();

            }
        });
        layout.getChildren().addAll(customerList,customerTableView);
        layout.setStyle("-fx-background-color:#808080;");
        StyledScene customerScene = new StyledScene(layout);
        stage.setScene(customerScene);
        stage.setTitle("Guitarshop FX-Search customer");

    }
    private void getTableView(TableView customerTableView){

        customerTableView.setEditable(true);
        customerTableView.getSelectionModel().setCellSelectionEnabled(false);
        customerTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Creating table column
        TableColumn firstName = new TableColumn("First Name");
        firstName.setMinWidth(50);
        firstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));

        TableColumn lastName = new TableColumn("Last Name");
        lastName.setMinWidth(50);
        lastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));

        TableColumn streetAddress = new TableColumn("StreetAddress");
        streetAddress.setMinWidth(50);
        streetAddress.setCellValueFactory(new PropertyValueFactory<Customer,String>("streetAddress"));

        TableColumn city = new TableColumn("City");
        city.setMinWidth(50);
        city.setCellValueFactory(new PropertyValueFactory<Customer, String>("city"));

        TableColumn phoneNumber = new TableColumn("Phone #");
        phoneNumber.setMinWidth(50);
        phoneNumber.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));

        TableColumn email = new TableColumn("Email");
        email.setMinWidth(100);
        email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        customerTableView.getColumns().addAll(firstName,lastName,streetAddress,city,phoneNumber,email);
        ObservableList<Customer> customer  = FXCollections.observableArrayList(userDao.getCustomers());
        customerTableView.setItems(customer);
    }


}
