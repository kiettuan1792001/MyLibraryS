package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import model.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
        int n = 3;
        Book book = new Book();
        book.setImageSrc("/img/01.png");
        book.setName("Lập trình hướng \nđối tượng Java");
        book.setAuthor("123");
        ls.add(book);
        for (int i = 1; i< n; i++){
            book = new Book();
            char check = (char)(i +'0');
            book.setImageSrc("/img/0" + check+ ".png");
            book.setName("Lập trình hướng \nđối tượng Java");
            book.setAuthor("123");
            ls.add(book);
        }

//        Them sach vao dashboard
//        ;
//
//        book = new Book();
//        book.setImageSrc("/img/02.png");
//        book.setName("Giáo trình lập trình hướng đối tượng với Java");
//        book.setAuthor("Trần Thị Minh Châu \nNguyễn Việt Hà");
//        ls.add(book);
//
//        book = new Book();
//        book.setImageSrc("/img/03.png");
//        book.setName("Giáo trình C++ và lập trình hướng đối tượng");
//        book.setAuthor("GS. Phạm Văn Ất \nLê Trường Thông");
//        ls.add(book);


        return ls;
    }


}
