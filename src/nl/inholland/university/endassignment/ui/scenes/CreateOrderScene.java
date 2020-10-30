package nl.inholland.university.endassignment.ui.scenes;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.inholland.university.endassignment.data.ArticleDao;
import nl.inholland.university.endassignment.data.DataSeed;
import nl.inholland.university.endassignment.models.*;
import nl.inholland.university.endassignment.ui.DashboardScene;
import nl.inholland.university.endassignment.ui.StyledScene;


public class CreateOrderScene {
    private Label firstName ;
    private Label lastName;
    private Label streetAddress;
    private Label city;
    private Label phoneNumber ;
    private Label emailAddress;
    private Button add;
    private Button delete;
    private Button confirm;
    private Button reset;
    private Stage stage;
    private ObservableList<Article> articles;
    public Stage getStage() {

        return stage;
    }
    CreateOrderScene self = this;
    Order order;


    public TableView<OrderItem> articleTableView = new TableView<OrderItem>();

    private ObservableList<OrderItem>  orderView = FXCollections.observableArrayList();

    public ObservableList<OrderItem> getOrderView() {
        return orderView;
    }

    private AddArticleScene articleScene;
    public CreateOrderScene(Person person){

        order = new Order();
        this.stage = new Stage();
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #b3c6ff;");
        Menu home = new Menu("Home");
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DashboardScene dashboardScene = new DashboardScene(person);
                dashboardScene.getStage().show();
            }
        });
        Menu sales = new Menu("Sales");
        MenuItem order = new MenuItem("Order");
        MenuItem listOrder = new MenuItem("List Orders");
        listOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                OrderDetailsScene orderDetailsScene = new OrderDetailsScene();
                orderDetailsScene.getStage().show();
            }
        });
        sales.getItems().addAll(order,listOrder);
        menuBar.getMenus().addAll(home,sales);
        TextField search = new TextField();
        Label createOrder = new Label("Create Order #");
        createOrder.setFont(new Font("Arial",20));
        Label customerLabel = new Label("Customer");
        customerLabel.setFont(new Font("Arial",13));
        customerLabel.setTextFill(Color.WHITE);
        Label articleLabel = new Label("Articles");
        articleLabel.setTextFill(Color.WHITE);
        Button searchCustomer = new Button("Search Customer");
        searchCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CustomerListScene customerListScene = new CustomerListScene(self);
                customerListScene.getStage().show();
            }
        });

        // making article table view

        //making customer info view

        VBox customerInfoVbox = new VBox();
        firstName = new Label("First Name");
        lastName = new Label("Last Name");
        streetAddress = new Label("Street Address");
        city = new Label("City");
        phoneNumber = new Label("Phone Number");
        emailAddress = new Label("Email Address");
        customerInfoVbox.getChildren().addAll(firstName,lastName,streetAddress,city,phoneNumber,emailAddress);
        customerInfoVbox.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));

        articleTableView.setItems(orderView);
        getArticleTableView(articleTableView);
        VBox vBox = new VBox(articleTableView);

        //Creating HBox for Buttons
        HBox hBoxbuttons = new HBox();
        hBoxbuttons.setSpacing(10);
        getAllButtons(hBoxbuttons,person);

        GridPane root = new GridPane();
        GridPane.setConstraints(menuBar,0,0);
        GridPane.setConstraints(createOrder,2,2);
        GridPane.setConstraints(search,3 ,3);
        GridPane.setConstraints(searchCustomer,4,3);
        GridPane.setConstraints(articleLabel,0,4);
        GridPane.setConstraints(vBox,0,5);
        GridPane.setConstraints(customerInfoVbox,4,5);
        GridPane.setConstraints(hBoxbuttons,2,7);
        root.setStyle("-fx-background-color:#808080;");
        root.getChildren().addAll(menuBar,search,searchCustomer,articleLabel,createOrder,vBox,customerInfoVbox,hBoxbuttons);
        StyledScene DashboardScene = new StyledScene(root);
        root.setMinHeight(500);
        root.setMinWidth(1100);
        stage.setTitle("Guitarshop fx- Create an Order");
        stage.setScene(DashboardScene);

    }
    private void getArticleTableView(TableView articleTableView) {
        articleTableView.setEditable(true);
        articleTableView.getSelectionModel().setCellSelectionEnabled(false);
        articleTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Creating table column
        TableColumn quantity = new TableColumn("Quantity");
        quantity.setMinWidth(50);
        quantity.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("articleAmount"));

        TableColumn brand = new TableColumn("Brand");
        brand.setMinWidth(50);
        brand.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("brand"));

        TableColumn model = new TableColumn("Model");
        model.setMinWidth(50);
        model.setCellValueFactory(new PropertyValueFactory<OrderItem,String>("model"));

        TableColumn acoustic = new TableColumn("Acoustic");
        acoustic.setMinWidth(50);
        acoustic.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("acoustic"));

        TableColumn type = new TableColumn("Type");
        type.setMinWidth(50);
        type.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("type"));

        TableColumn price = new TableColumn("Price");
        price.setMinWidth(50);
        price.setCellValueFactory(new PropertyValueFactory<OrderItem,String>("price"));

        articleTableView.getColumns().addAll(quantity,brand,model,acoustic,type,price);

    }


    public void customerInfo(Customer customer) {
      order.addCustomer(customer);
      firstName.setText("First Name:   "+ customer.getFirstName());
      lastName.setText("Last Name:   "+customer.getLastName());
      streetAddress.setText("Street address:   "+customer.getStreetAddress());
      city.setText("City:   "+customer.getCity());
      phoneNumber.setText("Phone Number:   "+customer.getPhoneNumber());
      emailAddress.setText("Email Address:   "+customer.getEmail());
    }
    private void getAllButtons(HBox hBoxButtons,Person person){
        add = new Button("Add");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                articleScene = new AddArticleScene(self);
                articleScene.getStage().show();
            }
        });
        delete = new Button("Delete");
        confirm = new Button("Confirm");
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ConfrimationScene scene = new ConfrimationScene(order);
                scene.getStage().show();
            }
        });
        reset = new Button("reset");
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DashboardScene dashboardScene = new DashboardScene(person);
                dashboardScene.getStage().show();
            }
        });
        hBoxButtons.getChildren().addAll(add,delete,confirm,reset);
    }
    public void articleInfo(Article article){

    }
}
