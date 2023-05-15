package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Button user_loginBtn;

    @FXML
    private TextField user_password;

    @FXML
    private TextField user_username;

    @FXML
    private ComboBox<String> user_select;

    private Connection connect ;
    private PreparedStatement prepare ;
    private ResultSet result ;

    private Alert alert;

    private void successMessage(String message){
        alert = new Alert((Alert.AlertType.INFORMATION));
        alert.setTitle("Information message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void errorMessage(String message){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void loginAccount() {

        // CHECK IF THERE ARE FIELDS THAT EMPTY
        if (user_username.getText().isEmpty() || user_password.getText().isEmpty()) {
            errorMessage("Please fill all blank fields");
        } else {
            String selectData = "SELECT * FROM user WHERE user_usernameID = ? and user_passwordID = ?"; // MAKE SURE TO CHECK IF THE NAME OF YOUR TABLE AND COLUMNS ARE MATCH

            connect = Connect.connectDB();

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, user_username.getText());
                prepare.setString(2, user_password.getText());

                result = prepare.executeQuery();

                if (result.next()) {
                    // IF CORRECT USERNAME AND PASSWORD

                    successMessage("Login Successfully!");

                    // LETS CREATE YOUR MAIN FORM
                    // LINK YOUR ADMIN MAIN FORM
                    Parent root = FXMLLoader.load(getClass().getResource("dashboardML.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("M");
                    stage.setScene(new Scene(root));

                    stage.show();

                    // TO HIDE YOUR ADMIN LOGIN FORM
                    user_loginBtn.getScene().getWindow().hide();
                } else {
                    // IF INCORRECT USERNAME OR PASSWORD

                    errorMessage("Incorrect Username/Password");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void switchForm() {
        try {
            Parent root = null;
            if (user_select.getSelectionModel().getSelectedItem().equals("Cá nhân")) {
                root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); // LINK YOUR LOGIN ADMIN PORTAl
            } else if (user_select.getSelectionModel().getSelectedItem().equals("Quản trị viên")) {
                root = FXMLLoader.load(getClass().getResource("AdminPortal.fxml"));
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 960, 540));

            stage.show();
            // TO HIDE THE TEACHER PORTAL FORM
            user_select.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public void selectUser() {

        List<String> listU = new ArrayList<>();

        for (String data : users.Users) {
            listU.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listU);
        user_select.setItems(listData);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectUser();
    }
}
