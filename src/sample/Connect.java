package sample;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author WINDOWS 10
 */
public class Connect {

    // FOR THE DATABASE
    public static Connection connectDB() {
// FIRST, LETS CREATE OUR DATABASE
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/multiuser", "root", ""); // LINK YOUR DATABASE, ROOT IS OUR DEFAULT USERNAME AND THE PASSWORD IS NULL OR EMPTY

            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}