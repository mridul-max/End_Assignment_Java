package nl.inholland.university.endassignment.ui.scenes;


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
import nl.inholland.university.endassignment.models.Article;
import nl.inholland.university.endassignment.models.Customer;
import nl.inholland.university.endassignment.models.Person;
import nl.inholland.university.endassignment.ui.StyledScene;


public class CreateOrderScene {
    Label firstName ;
    Label lastName;
    Label streetAddress;
    Label city;
    Label phoneNumber ;
    Label emailAddress;
    private Stage stage;
    private ObservableList<Article> articles;
    public Stage getStage() {
        return stage;
    }
    public CreateOrderScene(Person person){
        stage = new Stage();
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #b3c6ff;");
        Menu home = new Menu("Home");
        Menu sales = new Menu("Sales");
        MenuItem order = new MenuItem("Order");
        MenuItem listOrder = new MenuItem("List Orders");
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
        Button searchCustomer = new Button("Search");
        CreateOrderScene self = this;
        searchCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CustomerListScene customerListScene = new CustomerListScene(self);
                customerListScene.getStage().show();
            }
        });

        // making article table view
        TableView<Article> articleTableView = new TableView<>();

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

        getArticleTableView(articleTableView);
        VBox vBox = new VBox(articleTableView);

        GridPane root = new GridPane();
        GridPane.setConstraints(menuBar,0,0);
        GridPane.setConstraints(createOrder,2,2);
        GridPane.setConstraints(search,3 ,3);
        GridPane.setConstraints(searchCustomer,4,3);
        GridPane.setConstraints(articleLabel,0,4);
        GridPane.setConstraints(vBox,0,5);
        GridPane.setConstraints(customerInfoVbox,4,5);
        root.setStyle("-fx-background-color:#808080;");
        root.getChildren().addAll(menuBar,search,searchCustomer,articleLabel,createOrder,vBox,customerInfoVbox);
        StyledScene DashboardScene = new StyledScene(root);
        root.setMinHeight(500);
        root.setMinWidth(1200);
        stage.setTitle("Guitarshop fx- Create an Order");
        stage.setScene(DashboardScene);

    }
    private void getArticleTableView(TableView articleTableView){
        articleTableView.setEditable(true);
        articleTableView.getSelectionModel().setCellSelectionEnabled(false);
        articleTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Creating table column
        articleTableView.setItems(articles);
        TableColumn quantity = new TableColumn("Quantity");
        quantity.setMinWidth(50);
        quantity.setCellValueFactory(new PropertyValueFactory<Article, String>("Quantity"));

        TableColumn brand = new TableColumn("Brand");
        brand.setMinWidth(50);
        brand.setCellValueFactory(new PropertyValueFactory<Article, String>("Brand"));

        TableColumn model = new TableColumn("Model");
        model.setMinWidth(50);
        model.setCellValueFactory(new PropertyValueFactory<Article,String>("Model"));

        TableColumn acoustic = new TableColumn("Acoustic");
        acoustic.setMinWidth(50);
        acoustic.setCellValueFactory(new PropertyValueFactory<Article, String>("Acoustic"));

        TableColumn type = new TableColumn("Type");
        type.setMinWidth(50);
        type.setCellValueFactory(new PropertyValueFactory<Article, String>("Type"));

        TableColumn price = new TableColumn("Price");
        price.setMinWidth(50);
        price.setCellValueFactory(new PropertyValueFactory<Article,String>("Price"));

        articleTableView.getColumns().addAll(quantity,brand,model,acoustic,type,price);
    }
    public void customerInfo(Customer customer){
      firstName.setText("First Name:   "+ customer.getFirstName());
      lastName.setText("Last Name:   "+customer.getLastName());
      streetAddress.setText("Street address:   "+customer.getStreetAddress());
      city.setText("City:   "+customer.getCity());
      phoneNumber.setText("Phone Number:   "+customer.getPhoneNumber());
      emailAddress.setText("Email Address:   "+customer.getEmail());
    }
}
