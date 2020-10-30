package nl.inholland.university.endassignment.ui.scenes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.inholland.university.endassignment.data.DataSeed;
import nl.inholland.university.endassignment.data.OrderDao;
import nl.inholland.university.endassignment.models.Order;
import nl.inholland.university.endassignment.models.OrderItem;
import nl.inholland.university.endassignment.ui.StyledScene;

public class OrderDetailsScene {
    private Stage stage;
    public Stage getStage() {

        return stage;
    }
    OrderDao orderDao = new OrderDao(DataSeed.getDbInstance());
    TableView<OrderItem> detailsTableView;
    private ObservableList<OrderItem>  detailsData;
    public OrderDetailsScene(){
        stage = new Stage();
        GridPane gridPane = new GridPane();
        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(10);
        Label articleList = new Label("Order List");
        articleList.setTextFill(Color.WHITE);
        articleList.setFont(new Font("Arial",20));

        //Creating table View
        TableView<Order> orderTableView = new TableView<Order>();
        getOrderListView(orderTableView);
        layout.getChildren().addAll(articleList,orderTableView);
        GridPane.setConstraints(layout,0,0);


        VBox detailsVbox = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(30);
        Label details = new Label("Details");
        details.setTextFill(Color.WHITE);
        details.setFont(new Font("Arial",20));
        detailsTableView = new TableView<>();
        orderTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Order order = orderTableView.getSelectionModel().getSelectedItem();
                detailsData = FXCollections.observableArrayList(order.getOrderItems());
                detailsTableView.setItems(detailsData);
            }
        });
        getDetailsView(detailsTableView);
        detailsVbox.getChildren().addAll(details,detailsTableView);
        layout.setStyle("-fx-background-color:#808080;");
        GridPane.setConstraints(detailsVbox,0,3);
        gridPane.getChildren().addAll(layout,detailsVbox);
        StyledScene orderDetailScene = new StyledScene(gridPane);
        gridPane.setMaxHeight(600);
        gridPane.setMinWidth(600);
        stage.setScene(orderDetailScene);
        stage.setTitle("Guitarshop FX-Add article");

    }
    private void getOrderListView(TableView orderTableView){

        orderTableView.setEditable(true);
        orderTableView.getSelectionModel().setCellSelectionEnabled(false);
        orderTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Creating table column
        TableColumn orderId = new TableColumn(" Order# ");
        orderId.setMinWidth(30);
        orderId.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));

        TableColumn date = new TableColumn(" Date ");
        date.setMinWidth(50);
        date.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));

        TableColumn customerName = new TableColumn(" Customer Name ");
        customerName.setMinWidth(30);
        customerName.setCellValueFactory(new PropertyValueFactory<Order, String>("customerName"));


        TableColumn city = new TableColumn(" City ");
        city.setMinWidth(30);
        city.setCellValueFactory(new PropertyValueFactory<Order, String>("city"));

        TableColumn phone = new TableColumn(" Phone  ");
        phone.setMinWidth(30);
        phone.setCellValueFactory(new PropertyValueFactory<Order, String>("phone"));

        TableColumn email = new TableColumn(" Email Address ");
        email.setMinWidth(50);
        email.setCellValueFactory(new PropertyValueFactory<Order, String>("email"));

        TableColumn count = new TableColumn(" Count ");
        count.setMinWidth(50);
        count.setCellValueFactory(new PropertyValueFactory<Order, String>("itemCount"));

        orderTableView.getColumns().addAll(orderId,date,customerName,city,phone,email,count);
        ObservableList<Order> orders = FXCollections.observableArrayList(orderDao.getOrders());
        orderTableView.setItems(orders);

    }
    private void getDetailsView(TableView detailsTableView){

        detailsTableView.setEditable(true);
        detailsTableView.getSelectionModel().setCellSelectionEnabled(false);
        detailsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Creating table column
        TableColumn orderId = new TableColumn(" UuiD ");
        orderId.setMinWidth(50);
        orderId.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("orderId"));

        TableColumn brand = new TableColumn(" Brand ");
        brand.setMinWidth(50);
        brand.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("brand"));

        TableColumn model = new TableColumn(" Model ");
        model.setMinWidth(50);
        model.setCellValueFactory(new PropertyValueFactory<OrderItem,String>("model"));

        TableColumn acoustic = new TableColumn(" Acoustic ");
        acoustic.setMinWidth(50);
        acoustic.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("acoustic"));

        TableColumn type = new TableColumn(" Type  ");
        type.setMinWidth(50);
        type.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("type"));

        TableColumn price = new TableColumn(" price ");
        price.setMinWidth(50);
        price.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("price"));

        TableColumn quantity = new TableColumn(" Quantity ");
        quantity.setMinWidth(50);
        quantity.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("articleAmount"));

        detailsTableView.getColumns().addAll(orderId,brand,model,acoustic,type,price,quantity);

    }
}
