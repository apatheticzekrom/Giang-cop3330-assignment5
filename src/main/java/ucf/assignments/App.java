package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application
{
    // Launch code
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // More launch code
        try{
            Parent root = FXMLLoader.load(getClass().getResource("appGui.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Inventory");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
