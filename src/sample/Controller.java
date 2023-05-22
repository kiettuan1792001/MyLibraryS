package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Book;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.beans.EventHandler;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Controller implements Initializable {

    @FXML
    private HBox cardLayout;
    @FXML
    private HBox cardsearch;
    public List<Book> recentlyAdded;
    public List<Book> borrowed;
    private List<Book> loved;
    private List<Book> history;
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
    private Button History;

    @FXML
    private VBox vbox;
    @FXML
    private Label label;
    @FXML
    private Button logout;
    @FXML
    private Button filter;
    @FXML
    private ScrollPane scroll_book;
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
        classify.setValue("Tất cả");
        scroll_book.setVisible(true);
    }
    @FXML
    private void bookshelf(MouseEvent event){
        borrowed = new ArrayList<>(book_borrowed());
        System.out.println(borrowed.size());
        cardLayout.getChildren().clear();
        try {
            for (int i=0; i < borrowed.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cardML.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(borrowed.get(i));
                cardLayout.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp.setCenter(vbox);
        label.setText("Giá sách của tôi");
        classify.setValue("Tất cả");
        scroll_book.setVisible(true);
    }
    @FXML
    private void love(MouseEvent event){
        loved = new ArrayList<>(book_loved());
        System.out.println(loved.size());
        cardLayout.getChildren().clear();
        try {
            for (int i=0; i < loved.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cardML.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(loved.get(i));
                cardLayout.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp.setCenter(vbox);
        label.setText("Sách yêu thích của tôi");
        classify.setValue("Tất cả");
        scroll_book.setVisible(true);
    }
    @FXML
    private void history(MouseEvent event){
        history = new ArrayList<>(book_history());
        System.out.println(history.size());
        cardLayout.getChildren().clear();
        try {
            for (int i=0; i < history.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cardML.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(history.get(i));
                cardLayout.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bp.setCenter(vbox);
        label.setText("Lịch sử mượn sách");
        classify.setValue("Tất cả");
        scroll_book.setVisible(true);
    }
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
        if (book_result.size() == 0) {
            label.setText("Không tìm thấy kết quả nào cho: '" + text + "'");
            scroll_book.setVisible(false);
        } else if (text.equals("")){
            label.setText("Sách nổi bật");
            scroll_book.setVisible(true);
        }
        else {
            label.setText("Kết quả tìm kiếm cho: '" + text + "'");
            scroll_book.setVisible(true);
        }
        search_text.setText("");
        classify.setValue("Tất cả");
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
        classify.getItems().addAll(
                "Tất cả",
                "Công nghệ",
                "Kinh tế",
                "Luật",
                "Y học"
        );
        filter.setOnAction(e -> filterBook(classify.getValue().toString()));
        scroll_book.setVisible(true);
    }
    private void filterBook(String text){
        List<Book> book_filter = new ArrayList<>();
        if (text.equals("Tất cả")) {
            book_filter = recentlyAdded;
        } else {
            for (Book book : recentlyAdded) {
                if (book.getCategory().toLowerCase().contains(text.toLowerCase())) {
                    book_filter.add(book);
                }
            }
        }
        cardLayout.getChildren().clear();
        try {
            for (int i=0; i < book_filter.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cardML.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(book_filter.get(i));
                cardLayout.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (text.equals("Tất cả"))
            label.setText("Sách nổi bật");
        else
            label.setText("Các sách thuộc thể loại: '" + text + "'");
        scroll_book.setVisible(true);
    }

    public List<Book> recentlyAdded(){
        List<Book> books = new ArrayList<>();
        try {
            File path = new File("src/data/Raw");
            File[] file_list = path.listFiles();
            int index;
            Book book = new Book();
            int check = 0;
            for (File file : file_list){
                index = Integer.parseInt(file.getName().split("Data")[1].split(".txt")[0]);
                if (check == 0) {
                    book = new Book();
                } else {
                    check++;
                }
                book.setImageSrc("/img/0" + index + ".png");
                File file_read = new File("src/data/Raw/Data" + index + ".txt");
                Scanner myReader = new Scanner(file_read);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
    public List <Book> book_history(){
        List<Book> books = new ArrayList<>();
        try {
            File path = new File("src/data/History");
            File[] file_list = path.listFiles();
            int index;
            Book book = new Book();
            int check = 0;
            for (File file : file_list){
                index = Integer.parseInt(file.getName().split("Data")[1].split(".txt")[0]);
                if (check == 0) {
                    book = new Book();
                } else {
                    check++;
                }
                book.setImageSrc("/img/0" + index + ".png");
                File file_read = new File("src/data/History/Data" + index + ".txt");
                if(file_read.length() == 0){
                    continue;
                }
                Scanner myReader = new Scanner(file_read);
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
                file.exists();
            }
            path.exists();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
    public List<Book> book_borrowed(){
        List<Book> books = new ArrayList<>();
        try {
            File path = new File("src/data/Borrow");
            File[] file_list = path.listFiles();
            int index;
            Book book = new Book();
            int check = 0;
            for (File file : file_list){
                index = Integer.parseInt(file.getName().split("Data")[1].split(".txt")[0]);
                if (check == 0) {
                    book = new Book();
                } else {
                    check++;
                }
                book.setImageSrc("/img/0" + index + ".png");
                File file_read = new File("src/data/Borrow/Data" + index + ".txt");
                if(file_read.length() == 0){
                    continue;
                }
                Scanner myReader = new Scanner(file_read);
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
                file.exists();
            }
            path.exists();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
    public void borrowed(Book book_check){
        try{
            for (Book book : recentlyAdded){
                if (book_check.getID() == book.getID()){
                    int index = recentlyAdded.indexOf(book) + 1;
                    InputStream inputStream = new BufferedInputStream(new FileInputStream("src/data/Raw/Data" + index + ".txt"));
                    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("src/data/Borrow/Data" + index + ".txt"));
                    byte[] buffer = new byte[1024];
                    int lengthRead;
                    while ((lengthRead = inputStream.read(buffer)) > 0){
                        outputStream.write(buffer, 0, lengthRead);
                        outputStream.flush();
                    }
                    outputStream.close();
                    inputStream.close();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void give_back(Book book_check){
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream("src/data/Borrow/Data" + book_check.getID() + ".txt"));
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("src/data/History/Data" + book_check.getID() + ".txt"));
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, lengthRead);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File from = new File("src/data/Borrow/Data" + book_check.getID() + ".txt");
        from.exists();
        from.delete();
        try {
            FileOutputStream fos = new FileOutputStream(from);
            System.out.print("File was opened");
            // -> file was closed
        } catch(IOException e) {
            // -> file still open
            System.out.print("File was not opened");
        }
    }

    public void addLove(Book book_check) {
        try{
            for (Book book : recentlyAdded){
                if (book_check.getID() == book.getID()){
                    int index = recentlyAdded.indexOf(book) + 1;
                    InputStream inputStream = new BufferedInputStream(new FileInputStream("src/data/Raw/Data" + index + ".txt"));
                    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("src/data/Love/Data" + index + ".txt"));
                    byte[] buffer = new byte[1024];
                    int lengthRead;
                    while ((lengthRead = inputStream.read(buffer)) > 0){
                        outputStream.write(buffer, 0, lengthRead);
                        outputStream.flush();
                    }
                    outputStream.close();
                    inputStream.close();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void remove_love(Book book_check){
        try {
            File from = new File("src/data/Love/Data" + book_check.getID() + ".txt");
            from.exists();
            from.delete();
            FileOutputStream fos = new FileOutputStream(from);
        } catch(IOException e) {
        }
    }
    public List<Book> book_loved(){
        List<Book> books = new ArrayList<>();
        try {
            File path = new File("src/data/Love");
            File[] file_list = path.listFiles();
            int index;
            Book book = new Book();
            int check = 0;
            for (File file : file_list){
                index = Integer.parseInt(file.getName().split("Data")[1].split(".txt")[0]);
                if (check == 0) {
                    book = new Book();
                } else {
                    check++;
                }
                book.setImageSrc("/img/0" + index + ".png");
                File file_read = new File("src/data/Love/Data" + index + ".txt");
                if(file_read.length() == 0){
                    continue;
                }
                Scanner myReader = new Scanner(file_read);
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
                file.exists();
            }
            path.exists();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
    private List<Book> searchBook(String search_text){
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
