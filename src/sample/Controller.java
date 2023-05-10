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
}
