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
        Book book = new Book();
        int n = 2;
        for (int i = 1; i <= n; i++){
            if (i > 1) {
                book = new Book();
            }
            char check = (char) (i + '0');
            book.setImageSrc("/img/0" + check + ".png");
            try {
                File file = new File("/data/Data1.txt");
                Scanner myReader = new Scanner(file);
                book.setName(myReader.nextLine());
                int count = 0;
                while (myReader.hasNextLine()) {
                    count++;
                    //System.out.println(count);
                    String data = myReader.nextLine();
                    //System.out.println(data.split(": ", 2)[1]);
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
            } catch (FileNotFoundException e) {
            e.printStackTrace();
            }
            books.add(book);
        }

//        book.setImageSrc("/img/01.png");
//        book.setName("123456");
//        book.setAuthor("123");
//        books.add(book);
        return books;
    }
    public static void list_book(List<Book> books){
        String name = "";
        int ID = 0;
        String author = "";
        String category = "";
        String details = "";
        String ImageSrc = "";
        int n = 6;
        try {
            for (int i = 1; i <= n; i++) {
                char check = (char) (i + '0');
                File file = new File("/data/Data" + check + ".txt");
                Scanner myReader = new Scanner(file);
//                System.out.println(myReader.hasNextLine());
                int count = 0;
                while (myReader.hasNextLine()) {
                    count++;
                    //System.out.println(count);
                    String data = myReader.nextLine();
                    //System.out.println(data.split(": ", 2)[1]);
                    switch (count) {
                        case 1:
                            name = data.split(": ", 2)[1];
                            break;
                        case 2:
                            ID = Integer.parseInt(data.split(": ", 2)[1]);
                            break;
                        case 3:
                            author = data.split(": ", 2)[1];
                            break;
                        case 4:
                            category = data.split(": ", 2)[1];
                            break;
                        case 5:
                            details = data.split(": ", 2)[1];
                            break;
                        default:
                            break;
                    }
                }
                ImageSrc = "/img/0" + check + ".png";
                books.add(new Book(name, author, ID, category, details, ImageSrc));
                myReader.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
