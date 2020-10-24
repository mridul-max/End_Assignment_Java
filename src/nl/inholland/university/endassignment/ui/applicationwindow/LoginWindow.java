package nl.inholland.university.endassignment.ui.applicationwindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.university.endassignment.data.UserDao;
import nl.inholland.university.endassignment.models.Person;
import nl.inholland.university.endassignment.ui.DashboardScene;

public class LoginWindow {
    private Stage stage;
    private UserDao userDao;
    public Stage getStage() {
        return stage;
    }

    public LoginWindow(){
            stage = new Stage();
            HBox layout = new HBox();
            layout.setPadding(new Insets(200));
            layout.setSpacing(20);
            VBox loginPane = new VBox();
            loginPane.setPadding(new Insets(10,10,10,10));
            loginPane.setSpacing(10);
            Label userName = new Label();
            userName.setText("Username");
            Label password = new Label();
            password.setText("Password");
            Button loginbutton = new Button();
            loginbutton.setText("Login");
            TextField userField = new TextField();
            userField.setPromptText("Username");
            PasswordField passwordfield = new PasswordField();
            passwordfield.setPromptText("Password");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            loginbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    userDao = new UserDao();
                    String userName = userField.getText();
                    String password = passwordfield.getText();
                    if(userName.isBlank()||password.isBlank()){
                        alert.setTitle("Alert Message");
                        alert.setContentText("You should enter your username and password");
                        alert.show();
                    }
                    else{
                        Person p = userDao.getByCredentials(userName,password);
                        if(p == null){
                            alert.setTitle("Alert Message");
                            alert.setContentText("Incorrect username or password");
                            alert.show();
                        }
                        else{
                            DashboardScene dashboardScene = new DashboardScene(p);
                            stage.close();
                            dashboardScene.getStage().show();
                        }
                    }
                }
            });
            loginPane.getChildren().addAll(userName,userField,password,passwordfield,loginbutton);
            layout.getChildren().add(loginPane);
            Scene mainscene = new Scene(layout);
            stage.setTitle("Guitarshop fx- Login");
            stage.setScene(mainscene);
    }

}
