package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CardDetailController implements Initializable {
    @FXML
    private ImageView detailImage;
    @FXML
    private Label detailName;
    @FXML
    private Label detailAuthor;
    @FXML
    private Label detailDetail;
    @FXML
    public Button borrow;
    private Book bookSelected;
    public List<Book> book_borrowed;

    public void initData(Book book) {
        bookSelected = book;
        detailName.setText(bookSelected.getName());
        detailAuthor.setText(bookSelected.getAuthor());
        detailDetail.setText(bookSelected.getDetails());
        detailImage.setImage(new Image(bookSelected.getImageSrc()));
        Controller controller = new Controller();
        List<Book> check = controller.book_borrowed();
        for (Book book_check : check) {
            if (bookSelected.getID() == book_check.getID()) {
                borrow.setText("Trả sách");
            }
        }
    }
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        borrow.setText("check");

    }
    @FXML
    void borrow(ActionEvent event){
        if (borrow.getText().equals("Mượn sách")) {
            borrow.setText("Trả sách");
            Controller controller = new Controller();
            controller.recentlyAdded = controller.recentlyAdded();
            controller.borrowed(bookSelected);
        } else {
            borrow.setText("Mượn sách");
            Controller controller = new Controller();
            controller.recentlyAdded = controller.recentlyAdded();
            controller.give_back(bookSelected);
        }
//        List<Book> book_borrowed = controller.book_borrowed();
//        for (Book book : book_borrowed) {
//            if (bookSelected.getID() == book.getID()) {
//                borrow.setText("Trả sách");
//                borrowed = true;
//            }
//        }
    }
    @FXML
    void back(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("dashboardML.fxml"));
            Stage stage = new Stage();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 960, 540);
            stage.setScene(scene);
            stage.setTitle("Library System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
