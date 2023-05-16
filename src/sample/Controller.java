package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Book;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.beans.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML
    private HBox cardLayout;
    @FXML
    private HBox cardsearch;
    private List<Book> recentlyAdded;
    private List<Book> book_result;
    String text;
    @FXML
    private TextField search_text;
    @FXML
    private Button search_click;
    @FXML
    private BorderPane bp;
    @FXML
    private Button home;
    @FXML
    private Button bookshelf;
    @FXML
    private Button love;
    @FXML
    private ComboBox classify;
    @FXML
    private Button history;

    @FXML
    private VBox vbox;
    @FXML
    private Label label;
    @FXML
    private Button logout;
    @FXML
    private void home(MouseEvent event){
        book_result = new ArrayList<>(searchBook(" "));
        System.out.println(book_result.size());
        cardLayout.getChildren().clear();
        try {
            for (int i=0; i < book_result.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cardML.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(book_result.get(i));
                cardLayout.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp.setCenter(vbox);
        label.setText("Sách nổi bật");
    }
    @FXML
    private void bookshelf(MouseEvent event){
        loadPage("bookshelf");
    }
    @FXML
    private void love(MouseEvent event){
        loadPage("love");
    }
    @FXML
    private void history(MouseEvent event){
        loadPage("history");
    }
    //    @FXML
//    private void view(MouseEvent event){
//        loadPage("view");
//    }
    private void loadPage(String page){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
        bp.setCenter(root);
    }
    public void search(ActionEvent event){
        text = search_text.getText();
        book_result = new ArrayList<>(searchBook(text.toLowerCase()));
        System.out.println(book_result.size());
        cardLayout.getChildren().clear();
        try {
            for (int i=0; i < book_result.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cardML.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(book_result.get(i));
                cardLayout.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        bp.setCenter(vbox);
        label.setText("Kết quả tìm kiếm cho: '" + text + "'");
        search_text.setText("");
    }
    @FXML
    private void logout(ActionEvent event){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recentlyAdded = new ArrayList<>(recentlyAdded());
        try {
            for (int i=0; i < recentlyAdded.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cardML.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(recentlyAdded.get(i));
                cardLayout.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        classify = new ComboBox<>();
        classify.getItems().addAll(
                "Tất cả",
                "Công nghệ",
                "Kinh tế",
                "Luật",
                "Y học"
        );
    }

    private List<Book> recentlyAdded(){
        List<Book> books = new ArrayList<>();
        int n = 6;
        try {
            Book book = new Book();
            for (int i = 1; i <= n; i++){
                if (i > 1) {
                    book = new Book();
                }
                char check = (char) (i + '0');
                book.setImageSrc("/img/0" + check + ".png");
                File file = new File("src/data/Data" + check + ".txt");
                Scanner myReader = new Scanner(file);
                int count = 0;
                while (myReader.hasNextLine() == true) {
                    count++;
                    String data = myReader.nextLine();
                    switch (count) {
                        case 1:
                            book.setName(data.split(": ", 2)[1]);
                            break;
                        case 2:
                            book.setID(Integer.parseInt(data.split(": ", 2)[1]));
                            break;
                        case 3:
                            book.setAuthor(data.split(": ", 2)[1]);
                            break;
                        case 4:
                            book.setCategory(data.split(": ", 2)[1]);
                            break;
                        case 5:
                            book.setDetails(data.split(": ", 2)[1]);
                            break;
                        default:
                            break;
                    }
                }
                books.add(book);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return books;
    }

    private List<Book> searchBook(String search_text){
        recentlyAdded = new ArrayList<>(recentlyAdded());
        List<Book> book_result = new ArrayList<>();
        for (Book book : recentlyAdded) {
            if (book.getName().toLowerCase().contains(search_text)
                    || book.getCategory().toLowerCase().contains(search_text)
                    || book.getAuthor().toLowerCase().contains(search_text)
                    || book.getDetails().toLowerCase().contains(search_text)) {
                book_result.add(book);
            }
        }
        return book_result;
    }

}
