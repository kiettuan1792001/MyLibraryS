package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import model.Book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML
    private HBox cardLayout;
    private List<Book> recentlyAdded;


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
            } catch (IOException e)
            {
                e.printStackTrace();
            }

    }


        private List<Book> recentlyAdded(){
            List<Book> ls = new ArrayList<>();
//          Them sach vao dashboard
            Book book = new Book();
            book.setImageSrc("/img/01.png");
            book.setName("Lập trình hướng \nđối tượng Java");
            book.setAuthor("123");
            ls.add(book);

            book = new Book();
            book.setImageSrc("/img/01.png");
            book.setName("123456");
            book.setAuthor("123");
            ls.add(book);


            return ls;
        }

    }
