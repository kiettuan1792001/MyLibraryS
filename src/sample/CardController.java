package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Book;

import java.awt.*;

public class CardController {
    @FXML
    private Label authorName;

    @FXML
    private Label bookName;

    @FXML
    private HBox box;

    @FXML
    private ImageView bookImage;

    private String[] colors = {"B9E5FF"};

    public void setData(Book book){
        Image image = new Image(getClass().getResourceAsStream(book.getImageSrc()));
        bookImage.setImage(image);

        bookName.setText(book.getName());
        authorName.setText(book.getAuthor());
        box.setStyle("-fx-background-color: #ffffff");

    }
}
