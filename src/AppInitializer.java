import db.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
        try {
            DBConnection.getInstance().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("/view/LoginPage.fxml"));
            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.centerOnScreen();
            primaryStage.setTitle("In Memory Pos System");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("something wrong in file");
            e.printStackTrace();
        }
    }
}
