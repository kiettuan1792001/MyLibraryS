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


public class AdminPortalController implements Initializable {

    @FXML
    private Button admin_loginBtn;

    @FXML
    private TextField admin_password;

    @FXML
    private ComboBox<String> admin_select;

    @FXML
    private TextField admin_username;

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
        if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty()) {
            errorMessage("Điền thông tin tài khoản/mật khẩu");
        } else {
            String selectData = "SELECT * FROM admin WHERE username = ? and password = ?"; // MAKE SURE TO CHECK IF THE NAME OF YOUR TABLE AND COLUMNS ARE MATCH

            connect = Connect.connectDB();

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, admin_username.getText());
                prepare.setString(2, admin_password.getText());

                result = prepare.executeQuery();

                if (result.next()) {
                    // IF CORRECT USERNAME AND PASSWORD

                    successMessage("Đăng nhập thành công!");

                    // LETS CREATE YOUR MAIN FORM
                    // LINK YOUR ADMIN MAIN FORM

                    Parent root = FXMLLoader.load(getClass().getResource("dashboardML.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("M");
                    stage.setScene(new Scene(root));

                    stage.show();

                    // TO HIDE YOUR ADMIN LOGIN FORM
                    admin_loginBtn.getScene().getWindow().hide();
                } else {
                    // IF INCORRECT USERNAME OR PASSWORD

                    errorMessage("Nhập lại tên tài khoản/mật khẩu");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void switchForm(){
        try {
            Parent root = null;
            if (admin_select.getSelectionModel().getSelectedItem().equals("Quản trị viên")) {
                root = FXMLLoader.load(getClass().getResource("AdminPortal.fxml")); // LINK YOUR LOGIN ADMIN PORTAl
            } else if (admin_select.getSelectionModel().getSelectedItem().equals("Cá nhân")) {
                root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 960, 540));
            stage.setTitle("Library System");

            stage.show();
            // TO HIDE THE TEACHER PORTAL FORM
            admin_select.getScene().getWindow().hide();

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
        admin_select.setItems(listData);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectUser();
    }

}
