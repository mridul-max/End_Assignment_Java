package nl.inholland.university.endassignment.ui.scenes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.inholland.university.endassignment.data.DataSeed;
import nl.inholland.university.endassignment.data.OrderDao;
import nl.inholland.university.endassignment.models.Order;
import nl.inholland.university.endassignment.models.OrderItem;
import nl.inholland.university.endassignment.ui.StyledScene;

public class ConfrimationScene {

    private Stage stage;
    public Stage getStage() {
        return stage;
    }
    private Order order;
    private Label firstName;
    private Label lastName;
    private Label streetAddress;
    private Label city;
    private  Label phoneNumber;
    private Label emailAddress;
    private Label qty;
    private Label brand;
    private Label model;
    private  Label type;
    private Label price;
    private Label totalPriceLabel;
    private Button confirm;
    public ConfrimationScene(Order order){
        this.order = order;
    stage = new Stage();
    VBox layout = new VBox();
    layout.setPadding(new Insets(30));
    layout.setSpacing(20);
    firstName = new Label("First Name:  "+order.getCustomer().getFirstName());
    lastName = new Label("Last Name: "+order.getCustomer().getLastName());
    streetAddress = new Label("Street Address:  "+order.getCustomer().getStreetAddress());
    city = new Label("City:  "+order.getCustomer().getCity());
    phoneNumber = new Label("Phone Number:  "+order.getCustomer().getPhoneNumber());
    emailAddress = new Label("Email Address:  "+order.getCustomer().getEmail());

    HBox confirmationHbox = new HBox();
    confirmationHbox.setSpacing(10);

    qty = new Label("Qty ");
    brand = new Label("Brand");
    model = new Label("Model");
    type = new Label("Type");
    price = new Label("Price");
    totalPriceLabel = new Label("Total Price ");
    confirm = new Button("  Confirm  ");
    getArticleInfo();
    confirm.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            OrderDao orderDao = new OrderDao(DataSeed.getDbInstance());
            orderDao.insert(order);
            stage.close();
        }
    });
    confirmationHbox.getChildren().addAll(qty,brand,model,type,price,totalPriceLabel,confirm);
    layout.getChildren().addAll(firstName,lastName,streetAddress,city,phoneNumber,emailAddress,confirmationHbox);
    layout.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    layout.setMinHeight(600);
    layout.setMinWidth(600);
    StyledScene confirmationScene = new StyledScene(layout);
    stage.setScene(confirmationScene);
    stage.setTitle("Guitarshop FX - Confirmation order");

    }
    private void getArticleInfo(){
        for (OrderItem m:order.getOrderItems()
             ) {

            qty = new Label("qyt "+"\n "+m.getArticleAmount());
            brand = new Label("Brand "+"\n "+m.getBrand());
            model = new Label("Model"+"\n "+m.getModel());
            type = new Label("Type "+"\n "+m.getType());
            price = new Label("Price "+"\n "+m.getPrice());
            totalPriceLabel = new Label("totalPrice "+"\n"+m.getPrice()*m.getArticleAmount());

        }
    }
}
