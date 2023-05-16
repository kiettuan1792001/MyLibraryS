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
    private Book bookSelected;

    public void initData(Book book) {
        bookSelected = book;
        detailName.setText(bookSelected.getName());
        detailAuthor.setText(bookSelected.getAuthor());
        detailDetail.setText(bookSelected.getDetails());
        detailImage.setImage(new Image(bookSelected.getImageSrc()));
    }
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void back(ActionEvent event) {
        detailName.setText("The Alchemist");
        detailAuthor.setText("Paulo Coelho");
        detailDetail.setText("Lập trình cấu trúc là phương pháp tồ chức, phân chia chương trình thành các hàm, thủ tục. Chúng được dùng để xứ lý dữ liệu nhưng lại tách rời các cấu trúc dữ liệu. Thông qua các ngôn ngữ Foxpro, Pascal, C đa số những người làm Tin học đã khá quen biết với phương pháp lập trình này. Lập trình hướng đối tượng (object-oriented programming) dựa trên việc tổ chức chương trình thành các lớp (class). Khác với hàm và thủ tục, lớp là một đơn vị bao gồm cả dữ liệu và các phương thức xử lý. Vì vậy lớp có thể mô lả các thực thể một cách chân thực, đầy đủ cả phần dữ liệu và yêu cầu quản lý. Tư tưởng lập trình hướng đối tượng được áp dụng cho hầu hết các ngôn ngữ mới chạy trên môi trường Windows như Microsoft Access, Visual Basic, Visual C. Vì vậy việc nghiên cứu phương pháp lập trình mới này là rất cần thiết đối với tất cả những người quan tâm, yêu thích Tin học. C ra dời năm 1973 với mục đích ban đầu là để viết hệ điều hành Unix trên máy tính mini PDP. Sau đó C đã được sử đụng rộng rãi trên nhiều loại máy tính khác nhau và đã trở thành một ngôn ngừ lập trình cấu trúc rất được ưa chuộng trên toàn thế giới. Để đưa C vào thế giới hướng đối tượng, năm 1980 nhà khoa học người Mỹ B.Stroustrup đã cho ra đời một ngôn ngữ C mới có tên ban đầu là \"C cỏ lớp\", sau đó đến năm 1983 thì gọi là C++. Ngôn ngữ C là một sự phát triển mạnh mẽ của C. Trong C++ chẳng những đưa vào tất cả các khái niệm, công cụ của lập trình hướng đối tượng mà còn đưa vào nhiều khả năng mới mẻ cho hàm. Như vậy C++ là một ngôn ngữ lai cho phép tổ chức chương trình theo các lớp và các hàm. Có thể nói C++ đã thúc đẩy ngôn ngữ C vốn đã rất thuyết phục đi vào thế giới lập trình hướng đối tượng và C++ đã trở thành ngôn ngữ hướng đối tượng nổi bật trong những năm 90. Cuốn sách này sẽ trình bày một cách hệ thống các khái niệm của lập trình hướng đối tượng được cài đặt trong C++ như lớp, đối tượng (object), sự thừa kế (inheritance), tính tương ứng bội (polymorphism) và các khả năng mới trong xây dựng, sử dựng hàm như: đối tham chiếu, đối mặc định, hàm trùng tên, hàm toán tử. Có một số vấn đề còn ít được biết đến như cách xây dựng hàm với số đối bất định trong C cũng se được giới thiệu. Các chương lừ 1 đến 11 với cách giải thích tỉ mỉ và với gần 100 chương trình minh họa sẽ cung cấp cho bạn đọc các khái niệm, phương pháp và kinh nghiệm lập trình hướng đối tượng trên C++. Có một phụ lục cuối sách (Phụ lục 6) sẽ hệ thống ngắn gọn phương pháp phân tích, thiết kế và lập trình hướng đối tuợng trên hình diện chung.\n");
        Image image = new Image(getClass().getResourceAsStream("/img/552721.png"));
        detailImage.setImage(image);
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("dashboardML.fxml"));
            Stage stage = new Stage();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 960, 540);
            stage.setScene(scene);
            stage.setTitle("Book Details");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
