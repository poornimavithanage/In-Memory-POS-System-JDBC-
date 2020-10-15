package controller;

import db.DBConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {
    public TextField txtUserName;
    public TextField txtPassword;
    public AnchorPane root;

    public void initialize(){
        Platform.runLater(()->{             //why we use???
            txtUserName.requestFocus();
        });

    }

    public void btnLogin_onAction(ActionEvent actionEvent) {
        try {
            String username = txtUserName.getText();
            String password = txtPassword.getText();

            //Statement stm = connection.createStatement();
            //ResultSet rst = stm.executeQuery("SELECT * FROM User WHERE username='" + username + "' AND password='" + password + "'");
            PreparedStatement pstm = (PreparedStatement) DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Users WHERE username=? AND password=?");
            pstm.setObject(1, username);
            pstm.setObject(2, password);
            ResultSet rst = pstm.executeQuery();
            if (rst.next()){
                Parent root = FXMLLoader.load(this.getClass().getResource("/view/CustomerPage.fxml"));
                Scene mainScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(mainScene);
                primaryStage.centerOnScreen();
            }else{
                new Alert(Alert.AlertType.ERROR,"Invalid username or password, please try again", ButtonType.OK).show();
                txtUserName.requestFocus();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }

