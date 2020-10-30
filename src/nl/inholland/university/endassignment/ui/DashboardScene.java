package nl.inholland.university.endassignment.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.inholland.university.endassignment.models.OrderItem;
import nl.inholland.university.endassignment.models.Person;
import nl.inholland.university.endassignment.models.Role;
import nl.inholland.university.endassignment.ui.scenes.CreateOrderScene;
import nl.inholland.university.endassignment.ui.scenes.OrderDetailsScene;

import java.time.format.DateTimeFormatter;

public class DashboardScene {
    private Stage stage;
    public Stage getStage() {
        return stage;
    }
    MenuBar menuBar = new MenuBar();
    Menu home = new Menu("Home");
    public DashboardScene(Person person){
        stage = new Stage();
        getDashboardRole(person);
        Label loggedPerson = new Label();
        loggedPerson.setText("Welcome "+ person.getFirstName()+" "+ person.getLastName());
        loggedPerson.setPadding(new Insets(0));
        loggedPerson.setTextFill(Color.BLUE);
        loggedPerson.setFont(new Font("Arial",25));
        Label rolePerson = new Label();
        rolePerson.setText("Your role is: "+ person.getRole());
        Label currentDateTime = new Label();
        currentDateTime.setText("Today is: "+ person.getDateTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        GridPane root = new GridPane();
        GridPane.setConstraints(menuBar,0,0);
        GridPane.setConstraints(loggedPerson,2 ,1);
        GridPane.setConstraints(rolePerson,0,8);
        GridPane.setConstraints(currentDateTime,0,10);
        root.getChildren().addAll(menuBar,loggedPerson,rolePerson,currentDateTime);
        StyledScene DashboardScene = new StyledScene(root);
        stage.setTitle("Guitarshop fx- Dashboard");
        stage.setScene(DashboardScene);
        stage.setMinHeight(600);
        stage.setMinWidth(600);

    }
    private void getDashboardRole(Person person){
        menuBar.setStyle("-fx-background-color: #b3c6ff;");
        if(person.getRole() == Role.SALES){
            Menu sales = new Menu("Sales");
            MenuItem order = new MenuItem("Order");
            order.setOnAction(actionEvent -> {
                CreateOrderScene createOrderScene = new CreateOrderScene(person);
                stage.close();
                createOrderScene.getStage().show();
            });
            MenuItem listOrder = new MenuItem("List Orders");
            listOrder.setOnAction(actionEvent -> {
                OrderDetailsScene orderDetailsScene = new OrderDetailsScene();
                orderDetailsScene.getStage().show();
            });
            sales.getItems().addAll(order,listOrder);
            menuBar.getMenus().addAll(home,sales);
        }
        else if(person.getRole()== Role.MANAGER){
            Menu manager = new Menu("Sales");
            Menu stock   = new Menu("Stock");
            MenuItem listOrder = new MenuItem("List Orders");
            listOrder.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    OrderDetailsScene orderDetailsScene = new OrderDetailsScene();
                    orderDetailsScene.getStage().show();
                }
            });
            MenuItem maintain = new MenuItem("Maintain");
            manager.getItems().addAll(listOrder);
            stock.getItems().addAll(maintain);
            menuBar.getMenus().addAll(home,manager,stock);
        }
    }

}
