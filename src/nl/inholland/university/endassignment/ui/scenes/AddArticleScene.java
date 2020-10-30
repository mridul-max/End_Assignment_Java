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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.inholland.university.endassignment.App;
import nl.inholland.university.endassignment.data.ArticleDao;
import nl.inholland.university.endassignment.data.DataSeed;
import nl.inholland.university.endassignment.models.Article;
import nl.inholland.university.endassignment.models.Customer;
import nl.inholland.university.endassignment.models.OrderItem;
import nl.inholland.university.endassignment.models.Type;
import nl.inholland.university.endassignment.ui.StyledScene;

import java.util.List;

public class AddArticleScene {
    ArticleDao articleDao = new ArticleDao(DataSeed.getDbInstance());
    private Stage stage;
    public Stage getStage() {

        return stage;
    }
    private TextField addQuantity;
    private Button add;
    private Button cancel;
    private CreateOrderScene orderScene;
    private OrderItem orderItem;
    private ObservableList<Article>articlesView;
    private TableView<Article> articleTableView;

    public AddArticleScene(CreateOrderScene createOrderScene){
        stage = new Stage();
        this.orderScene = createOrderScene;
        VBox layout = new VBox();
        layout.setPadding(new Insets(30));
        layout.setSpacing(20);
        Label articleList = new Label("Article List");
        articleList.setTextFill(Color.WHITE);
        articleList.setFont(new Font("Arial",20));


        //Creating table View
        articleTableView = new TableView<>();
//        articleTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Article>() {
//            @Override
//            public void changed(ObservableValue<? extends Article> observableValue, Article article, Article newArticle) {
//                articlesView = FXCollections.observableArrayList(newArticle);
//                orderScene.articleTableView.setItems(articlesView);
//            }
//        });
        getTableView(articleTableView);
        HBox buttonHbox = new HBox();
        addQuantity = new TextField();
        add = new Button(" Add ");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Article article = articleTableView.getSelectionModel().getSelectedItem();
                int quantity = Integer.parseInt(addQuantity.getText());
                orderItem = new OrderItem(orderScene.order, article, quantity);
                orderScene.order.addOrderItem(orderItem);
                orderScene.getOrderView().add(orderItem);

            }
        });
        cancel = new Button(" cancel ");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });

        buttonHbox.setSpacing(10);
        buttonHbox.getChildren().addAll(addQuantity,add,cancel);
        layout.getChildren().addAll(articleList,articleTableView,buttonHbox);
        layout.setStyle("-fx-background-color:#808080;");
        StyledScene articleScene = new StyledScene(layout);
        stage.setScene(articleScene);
        stage.setTitle("Guitarshop FX-Add article");

    }
    private void getTableView(TableView articleTableView){

        articleTableView.setEditable(true);
        articleTableView.getSelectionModel().setCellSelectionEnabled(false);
        articleTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Creating table column
        TableColumn brand = new TableColumn(" Brand ");
        brand.setMinWidth(50);
        brand.setCellValueFactory(new PropertyValueFactory<Article, String>("brand"));

        TableColumn model = new TableColumn(" Model ");
        model.setMinWidth(50);
        model.setCellValueFactory(new PropertyValueFactory<Article, String>("model"));

        TableColumn acoustic = new TableColumn(" Acoustic ");
        acoustic.setMinWidth(50);
        acoustic.setCellValueFactory(new PropertyValueFactory<Article,String>("acoustic"));

        TableColumn type = new TableColumn(" Type ");
        type.setMinWidth(50);
        type.setCellValueFactory(new PropertyValueFactory<Article, String>("type"));

        TableColumn price = new TableColumn(" Price ");
        price.setMinWidth(50);
        price.setCellValueFactory(new PropertyValueFactory<Article, String>("price"));


        articleTableView.getColumns().addAll(brand,model,acoustic,type,price);
        ObservableList<Article> articles  = FXCollections.observableArrayList(articleDao.getArticles());
        articleTableView.setItems(articles);
    }

}
