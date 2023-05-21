package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Book;

import java.io.IOException;

public class CardController {
    @FXML
    private Label authorName;

    @FXML
    private Label bookName;

    @FXML
    private HBox box;
    @FXML
    private Button view;
    @FXML
    private ImageView bookImage;
    @FXML
    private BorderPane bp;
    private Book bookCheck;
    private String[] colors = {"B9E5FF"};

    public void setData(Book book){
        Image image = new Image(getClass().getResourceAsStream(book.getImageSrc()));
        bookImage.setImage(image);
        bookName.setText(book.getName());
        authorName.setText(book.getAuthor());
        box.setStyle("-fx-background-color: #ffffff");
        bookCheck = book;
    }

    @FXML
    private void view(ActionEvent event){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CardDetail.fxml"));
            Parent tableViewParent = loader.load();
            Scene tableViewScene = new Scene(tableViewParent);
            CardDetailController controller = loader.getController();
            controller.initData(bookCheck);
//            controller.borrow.setText("check");
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.setTitle("Book Details");
            window.show();
//            root = FXMLLoader.load(getClass().getResource("CardDetail.fxml"));
//            Stage stage = new Stage();
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root, 960, 540);
//            stage.setScene(scene);
//            stage.setTitle("Book Details");
//            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
