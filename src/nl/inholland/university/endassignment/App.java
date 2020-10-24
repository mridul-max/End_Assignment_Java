package nl.inholland.university.endassignment;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.inholland.university.endassignment.ui.applicationwindow.LoginWindow;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        LoginWindow mainwindow = new LoginWindow();
        mainwindow.getStage().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
